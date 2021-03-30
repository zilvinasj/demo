package com.demo.top.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.demo.top.model.album.Album;
import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.Artist;
import com.demo.top.model.artist.ArtistSearchResponse;
import com.demo.top.model.response.ApplicationAlbumResponse;
import com.demo.top.model.response.ApplicationArtistResponse;
import com.demo.top.model.token.Token;
import com.demo.top.spotify.SpotifyTokenGateway;
import com.demo.top.utility.FileUtils;
import com.demo.top.utils.TestUtils;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
        initMocks(this);
        when(spotifyTokenGateway.getToken()).thenReturn(getToken());
    }

    @Value("classpath:__files/spotifyArtistSearchResponse.json")
    private Resource spotifyArtistSearchResponse;

    @Value("classpath:__files/spotifyAlbumSearchResponse.json")
    private Resource spotifyAlbumSearchResponse;

    @MockBean
    private SpotifyTokenGateway spotifyTokenGateway;

    @Test
    void artistSearchSuccess() throws Exception {
        stubFor(WireMock.get(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("spotifyArtistSearchResponse.json")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8090/v1/artists/Kanye");


        String response = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());

        List<Artist> artists = TestUtils.getStringAsObject(response, ApplicationArtistResponse.class).getArtists();

        String mockContent = FileUtils.resourceAsString(spotifyArtistSearchResponse);

        List<String> expectedNames = TestUtils.getStringAsObject(mockContent, ArtistSearchResponse.class)
                .getArtists()
                .getItems()
                .stream()
                .map(Artist::getName)
                .collect(Collectors.toList());

        List<String> actualNames = artists.stream()
                .map(Artist::getName)
                .collect(Collectors.toList());

        // Make another call to check if cache is used
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        assertEquals(expectedNames, actualNames);

        // Will only be called once if cache is used (anyUrl can be matched as only one is mocked)
        WireMock.verify(1, getRequestedFor(anyUrl()));
    }

    @Test
    void albumSearchSuccess() throws Exception {
        stubFor(WireMock.get(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("spotifyAlbumSearchResponse.json")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8090/v1/albums/{album}","YMCA");


        String response = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

        List<Album> albums = TestUtils.getStringAsObject(response, ApplicationAlbumResponse.class).getAlbums();

        String mockContent = FileUtils.resourceAsString(spotifyAlbumSearchResponse);

        List<String> expectedNames = TestUtils.getStringAsObject(mockContent, AlbumSearchResponse.class)
                .getAlbums()
                .getItems()
                .stream()
                .map(Album::getName)
                .collect(Collectors.toList());

        List<String> matchedNames = albums.stream()
                .map(Album::getName)
                .filter(expectedNames::contains)
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

    public Token getToken() {
        return new Token("1234","1234","1234","1234");
    }

}
