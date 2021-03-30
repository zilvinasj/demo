package com.demo.top.spotify;

import com.demo.top.model.album.AlbumSearchResponse;
import com.demo.top.model.artist.ArtistSearchResponse;
import com.demo.top.model.token.Token;
import com.demo.top.model.track.TrackSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private final SpotifyGateway spotifyGateway;

    private final SpotifyTokenGateway tokenGateway;

    @Autowired
    public SpotifyService(SpotifyGateway spotifyGateway,
        SpotifyTokenGateway tokenGateway) {
        this.spotifyGateway = spotifyGateway;
        this.tokenGateway = tokenGateway;
    }

    public ArtistSearchResponse findArtists(String artistName) {
        Token token = tokenGateway.getToken();
        return spotifyGateway.getArtists(artistName, token);
    }

    public AlbumSearchResponse findAlbums(String albumName) {
        Token token = tokenGateway.getToken();
        return spotifyGateway.getAlbums(albumName, token);
    }

    public TrackSearchResponse findTracks(String trackName) {
        Token token = tokenGateway.getToken();
        return spotifyGateway.getTracks(trackName, token);
    }
}
