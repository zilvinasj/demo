package com.demo.top.model.track;

import com.demo.top.model.SpotifyGenericResponse;

public class TrackSearchResponse {

  private SpotifyGenericResponse<Track> tracks;

  public SpotifyGenericResponse<Track> getTracks() {
    return tracks;
  }

  public void setTracks(SpotifyGenericResponse<Track> tracks) {
    this.tracks = tracks;
  }
}
