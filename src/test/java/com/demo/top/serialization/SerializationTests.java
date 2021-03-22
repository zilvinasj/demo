package com.demo.top.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import com.demo.top.model.album.Album;
import com.demo.top.model.track.Track;
import com.demo.top.utility.FileUtils;
import com.demo.top.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest
class SerializationTests {

  @Value("classpath:__files/spotifyAlbumExample.json")
  private Resource spotifyAlbumExample;

  @Value("classpath:__files/spotifyTrackExample.json")
  private Resource spotifyTrackExample;

  @Test
  void givenTrack_whenDeserializeTrack_thenContentsMatch() throws Exception {

    Track expected = new Track();
    expected.setPopularity(77L);
    expected.setTrackNumber(9L);
    expected.setName("Flashing Lights");
    expected.setId("31I3Rt1bPa2LrE74DdNizO");

    Track actual = TestUtils.getStringAsObject(FileUtils.resourceAsString(spotifyTrackExample), Track.class);

    assertThat(actual).isEqualToComparingFieldByField(expected);
  }

  @Test
  void givenAlbum_whenDeserializeAlbum_thenContentsMatch() throws Exception {
    Album expected = new Album();
    expected.setDate("1978-01-01");
    expected.setName("YMCA");
    expected.setId("3I3YSq7ArvlwK3l49pq4oE");
    expected.setTotalTracks(6L);

    Album actual = TestUtils.getStringAsObject(FileUtils.resourceAsString(spotifyAlbumExample), Album.class);

    assertThat(actual).isEqualToComparingFieldByField(expected);

  }

}
