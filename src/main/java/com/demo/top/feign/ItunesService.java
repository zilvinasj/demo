package com.demo.top.feign;

import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.ArtistSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${top-application.itunes.url}", name = "itunes")
public interface ItunesService {

    @GetMapping("/search?entity=musicArtist")
    ArtistSearchResponse findArtist(@RequestParam("term") String artistName);

    @GetMapping("/lookup?entity=album")
    AlbumSearchResponse findTopAlbumsForArtist(@RequestParam Long amgArtistId, @RequestParam Integer limit);
}
