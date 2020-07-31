package com.demo.top.service;

import com.demo.top.exception.PlaylistNotFoundException;
import com.demo.top.feign.ItunesService;
import com.demo.top.model.dto.PlaylistDTO;
import com.demo.top.model.dto.SongDTO;
import com.demo.top.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlaylistService {

    private final ItunesService itunesService;
    private final PlaylistRepository playlistRepository;

    private static final Logger log = LoggerFactory.getLogger(PlaylistService.class);

    public PlaylistService(ItunesService itunesService, PlaylistRepository playlistRepository) {
        this.itunesService = itunesService;
        this.playlistRepository = playlistRepository;
    }

    public PlaylistDTO get(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(PlaylistNotFoundException::new);
    }

    public PlaylistDTO add(PlaylistDTO playlistDTO) {
        return playlistRepository.save(playlistDTO);
    }

    public PlaylistDTO updateSongs(Long id, Set<SongDTO> songs) {
        PlaylistDTO playlist = playlistRepository.findById(id).orElseThrow(PlaylistNotFoundException::new);

        playlist.getSongs().addAll(songs);

        return playlistRepository.save(playlist);
    }

    public PlaylistDTO updateName(Long id, String name) {

        PlaylistDTO playlist = playlistRepository.findById(id).orElseThrow(PlaylistNotFoundException::new);

        playlist.setName(name);

        playlistRepository.save(playlist);

        return playlist;
    }

}
