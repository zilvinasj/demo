package com.demo.top.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@ConfigurationProperties(prefix = "caching")
public class CacheConfiguration {

    private Map<String, CacheSpec> specs;

    private static final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        SimpleCacheManager manager = new SimpleCacheManager();
        if (specs != null) {
            List<CaffeineCache> caches =
                    specs.entrySet().stream()
                            .map(entry -> buildCache(entry.getKey(),
                                    entry.getValue(),
                                    ticker))
                            .collect(Collectors.toList());
            manager.setCaches(caches);
        }
        return manager;
    }

    private CaffeineCache buildCache(String name, CacheSpec cacheSpec, Ticker ticker) {
        log.info("Cache {} specified timeout of {} min, max of {}", name, cacheSpec.getTimeout(), cacheSpec.getMax());

        final Caffeine<Object, Object> caffeineBuilder
                = Caffeine.newBuilder()
                .expireAfterWrite(cacheSpec.getTimeout(), TimeUnit.DAYS)
                .maximumSize(cacheSpec.getMax())
                .ticker(ticker);

        return new CaffeineCache(name, caffeineBuilder.build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }

    public void setSpecs(
        Map<String, CacheSpec> specs) {
        this.specs = specs;
    }

    public static class CacheSpec {
        private Integer timeout;
        private Integer max = 200;


        public Integer getTimeout() {
            return timeout;
        }

        public void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }
    }

}