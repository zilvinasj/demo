package com.demo.top.model.response;

import com.demo.top.model.album.Album;

import java.util.List;

public class ApplicationAlbumResponse {
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
