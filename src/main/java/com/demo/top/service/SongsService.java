package com.demo.top.service;

import com.demo.top.itunes.ItunesGateway;
import com.demo.top.model.song.Song;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongsService {

  private final ItunesGateway itunesGateway;

  @Autowired
  public SongsService(ItunesGateway itunesGateway) {
    this.itunesGateway = itunesGateway;
  }

  public List<Song> getSongsByName(String songName) {
    return itunesGateway.getSongs(songName).getResults();
  }

}
