package com.demo.top.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.demo.top.model.response.ApplicationSongResponse;
import com.demo.top.model.token.Token;
import com.demo.top.model.track.Track;
import com.demo.top.model.track.TrackSearchResponse;
import com.demo.top.spotify.SpotifyTokenGateway;
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
class TrackIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SpotifyTokenGateway spotifyTokenGateway;

  @BeforeEach
  public void setup() {
    WireMock.reset();
    initMocks(this);
    when(spotifyTokenGateway.getToken()).thenReturn(getToken());
  }

  @Value("classpath:__files/spotifyTrackSearchResponse.json")
  private Resource spotifyTrackSearchResponse;

  @Test
  void songSearchSuccess() throws Exception {
    stubFor(WireMock.get(anyUrl())
        .willReturn(aResponse()
            .withStatus(200)
            .withBodyFile("spotifyTrackSearchResponse.json")));

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("http://localhost:8090/v1/tracks/Kanye");

    String response = mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

    List<Track> tracks = TestUtils.getStringAsObject(response, ApplicationSongResponse.class).getSongs();

    String mockContent = FileUtils.resourceAsString(spotifyTrackSearchResponse);

    List<String> expectedNames = TestUtils.getStringAsObject(mockContent, TrackSearchResponse.class)
        .getTracks()
        .getItems()
        .stream()
        .map(Track::getName)
        .collect(Collectors.toList());

    List<String> matchedNames = tracks.stream()
        .map(Track::getName)
        .filter(expectedNames::contains)
        .collect(Collectors.toList());

    // Make another call to check if cache is used
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

    assertEquals(expectedNames, matchedNames);
  }

  public Token getToken() {
    return new Token("1234","1234","1234","1234");
  }


}
