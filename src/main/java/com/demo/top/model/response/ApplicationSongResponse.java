package com.demo.top.model.response;

import com.demo.top.model.song.Song;
import java.util.List;

public class ApplicationSongResponse {
  private List<Song> songs;

  public List<Song> getSongs() {
    return songs;
  }

  public void setSongs(List<Song> songs) {
    this.songs = songs;
  }
}
