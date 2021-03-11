package com.demo.top.model.album;

import com.demo.top.model.SpotifyGenericResponse;

public class AlbumSearchResponse {
  private SpotifyGenericResponse<Album> albums;

  public SpotifyGenericResponse<Album> getAlbums() {
    return albums;
  }

  public void setAlbums(SpotifyGenericResponse<Album> albums) {
    this.albums = albums;
  }
}
