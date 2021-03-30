package com.demo.top.service.track;

import com.demo.top.model.track.Track;
import com.demo.top.spotify.SpotifyService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

  private final SpotifyService spotifyService;

  public TrackService(SpotifyService spotifyService) {
    this.spotifyService = spotifyService;
  }


  public List<Track> getTracksByName(String trackName) {
    return spotifyService.findTracks(trackName).getTracks().getItems();
  }

}
