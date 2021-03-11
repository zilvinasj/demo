package com.demo.top.model.response;

import com.demo.top.model.track.Track;
import java.util.List;

public class ApplicationSongResponse {
  private List<Track> tracks;

  public List<Track> getSongs() {
    return tracks;
  }

  public void setSongs(List<Track> tracks) {
    this.tracks = tracks;
  }
}
