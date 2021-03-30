package com.demo.top.model.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Album {

    public Album() {}

    private String name;

    private String id;

    @JsonProperty("total_tracks")
    private Long totalTracks;

    @JsonProperty("release_date")
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(Long totalTracks) {
        this.totalTracks = totalTracks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
