package com.demo.top.domain.artist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FavoriteArtist {

  @Size(min = 1, max = 80, message = "Artists name must be between 1 and 80 characters")
  @NotNull(message = "Artist name cannot be null")
  private String artistName;

  @NotNull(message = "Artist id cannot be null")
  private Long artistId;

  @NotNull(message = "AMG artist id cannot be null")
  private Long amgArtistId;

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public Long getArtistId() {
    return artistId;
  }

  public void setArtistId(Long artistId) {
    this.artistId = artistId;
  }

  public Long getAmgArtistId() {
    return amgArtistId;
  }

  public void setAmgArtistId(Long amgArtistId) {
    this.amgArtistId = amgArtistId;
  }
}
