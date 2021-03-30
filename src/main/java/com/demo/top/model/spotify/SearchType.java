package com.demo.top.model.spotify;

public enum SearchType {

  ALBUM("album"),
  ARTIST("artist"),
  TRACK("track");

  private final String type;

  SearchType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
