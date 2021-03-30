package com.demo.top.model.track;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Track {

  private Long popularity;

  @JsonProperty("track_number")
  private Long trackNumber;

  private String id;

  private String name;

  public Long getTrackNumber() {
    return trackNumber;
  }

  public void setTrackNumber(Long trackNumber) {
    this.trackNumber = trackNumber;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getPopularity() {
    return popularity;
  }

  public void setPopularity(Long popularity) {
    this.popularity = popularity;
  }
}
