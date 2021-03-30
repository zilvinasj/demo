package com.demo.top.model.artist;

import com.demo.top.model.SpotifyGenericResponse;

public class ArtistSearchResponse {

  private SpotifyGenericResponse<Artist> artists;

  public SpotifyGenericResponse<Artist> getArtists() {
    return artists;
  }

  public void setArtists(SpotifyGenericResponse<Artist> artists) {
    this.artists = artists;
  }

}
