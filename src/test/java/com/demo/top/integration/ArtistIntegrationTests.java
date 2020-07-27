package com.demo.top.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.demo.top.model.album.Album;
import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.Artist;
import com.demo.top.model.artist.ArtistSearchResponse;
import com.demo.top.model.response.ApplicationAlbumResponse;
import com.demo.top.model.response.ApplicationArtistResponse;
import com.demo.top.utility.FileUtils;
import com.demo.top.utils.TestUtils;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.common.base.Charsets;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureWireMock(port = 8090)
@AutoConfigureMockMvc
@SpringBootTest
class ArtistIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        WireMock.reset();
        //MockitoAnnotations.initMocks(this);
    }

    @Value("classpath:__files/itunesArtistResponse.json")
    private Resource itunesArtistResponse;

    @Value("classpath:__files/itunesAlbumResponse.json")
    private Resource itunesAlbumResponse;

    @Test
    void artistSearchSuccess() throws Exception {
        stubFor(WireMock.get(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("itunesArtistResponse.json")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8090/v1/artist/Kanye");


        String response = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        List<Artist> artists = TestUtils.getStringAsObject(response, ApplicationArtistResponse.class).getArtists();

        String mockContent = FileUtils.resourceAsString(itunesArtistResponse);

        List<String> expectedNames = TestUtils.getStringAsObject(mockContent, ArtistSearchResponse.class)
                .getResults()
                .stream()
                .map(Artist::getArtistName)
                .collect(Collectors.toList());

        List<String> matchedNames = artists.stream()
                .filter(it -> expectedNames.contains(it.getArtistName()))
                .map(Artist::getArtistName)
                .collect(Collectors.toList());

        // Make another call to check if cache is used
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        assertEquals(expectedNames, matchedNames);

        // Will only be called once if cache is used (anyUrl can be matched as only one is mocked)
        WireMock.verify(1, getRequestedFor(anyUrl()));
    }

    @Test
    void albumSearchSuccess() throws Exception {
        stubFor(WireMock.get(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("itunesAlbumResponse.json")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8090/v1/artist/0/albums");


        String response = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        List<Album> albums = TestUtils.getStringAsObject(response, ApplicationAlbumResponse.class).getAlbums();

        String mockContent = FileUtils.resourceAsString(itunesAlbumResponse);

        List<String> expectedNames = TestUtils.getStringAsObject(mockContent, AlbumSearchResponse.class)
                .getResults()
                .stream()
                .map(Album::getCollectionName)
                .collect(Collectors.toList());

        List<String> matchedNames = albums.stream()
                .filter(it -> expectedNames.contains(it.getCollectionName()))
                .map(Album::getCollectionName)
                .collect(Collectors.toList());

        // Make another call to check if cache is used
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        assertEquals(expectedNames, matchedNames);

        // Will only be called once if cache is used (anyUrl can be matched as only one is mocked)
        WireMock.verify(1, getRequestedFor(anyUrl()));

    }

}
