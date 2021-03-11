package com.demo.top.controller;

import com.demo.top.model.response.ApplicationSongResponse;
import com.demo.top.model.track.Track;
import com.demo.top.service.track.TrackService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/tracks")
public class TracksController {

  private final TrackService trackService;

  private static final Logger log = LoggerFactory.getLogger(TracksController.class);

  @Autowired
  public TracksController(TrackService trackService) {
    this.trackService = trackService;
  }

  @GetMapping(value = "/{trackName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApplicationSongResponse> searchForSongs(@PathVariable String trackName) {
    log.info("Searching for tracks: {}", trackName);
    List<Track> songsByName = trackService.getTracksByName(trackName);
    log.debug("Returning tracks for name: {}", trackName);

    ApplicationSongResponse response = new ApplicationSongResponse();
    response.setSongs(songsByName);

    return ResponseEntity.ok(response);
  }


}
