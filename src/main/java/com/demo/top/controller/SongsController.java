package com.demo.top.controller;

import com.demo.top.feign.ItunesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class SongsController {

    private final ItunesService userService;

    public SongsController(ItunesService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{songName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFavouriteArtists(@PathVariable String songName) {
        return ResponseEntity.ok(userService.findTracks(songName, 5));
    }

}
