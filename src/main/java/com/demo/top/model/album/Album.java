package com.demo.top.model.album;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.net.URL;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Album {

    private String collectionName;

    private Long collectionId;

    private URL artworkUrl60;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public URL getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(URL artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }
}
