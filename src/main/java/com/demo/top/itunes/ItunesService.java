package com.demo.top.itunes;

import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.ArtistSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class ItunesService {

    private ItunesGateway itunesGateway;

    public ItunesService(ItunesGateway itunesGateway) {
        this.itunesGateway = itunesGateway;
    }

    public ArtistSearchResponse findArtist(String artistName) {
        return itunesGateway.getArtists(artistName);
    }

    public AlbumSearchResponse findTopAlbumsForArtist(Long amgArtistId, Integer limit) {
        return itunesGateway.getAlbums(amgArtistId, limit);
    }
}
