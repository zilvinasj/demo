package com.demo.top.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.demo.top.model.response.ApplicationSongResponse;
import com.demo.top.model.song.Song;
import com.demo.top.model.song.SongSearchResponse;
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
public class SongIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    WireMock.reset();
  }

  @Value("classpath:__files/itunesSongResponse.json")
  private Resource itunesSongResponse;

  @Test
  void songSearchSuccess() throws Exception {
    stubFor(WireMock.get(anyUrl())
        .willReturn(aResponse()
            .withStatus(200)
            .withBodyFile("itunesSongResponse.json")));

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("http://localhost:8090/v1/songs/YMCA");

    String response = mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

    List<Song> songs = TestUtils.getStringAsObject(response, ApplicationSongResponse.class).getSongs();

    String mockContent = FileUtils.resourceAsString(itunesSongResponse);

    List<String> expectedNames = TestUtils.getStringAsObject(mockContent, SongSearchResponse.class)
        .getResults()
        .stream()
        .map(Song::getTrackName)
        .collect(Collectors.toList());

    List<String> matchedNames = songs.stream()
        .filter(it -> expectedNames.contains(it.getTrackName()))
        .map(Song::getTrackName)
        .collect(Collectors.toList());

    // Make another call to check if cache is used
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(Charsets.UTF_8);

    assertEquals(expectedNames, matchedNames);
  }


}
