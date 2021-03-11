package com.demo.top.service;

import com.demo.top.domain.artist.FavoriteArtist;
import com.demo.top.exception.UserNotFoundException;
import com.demo.top.model.artist.Artist;
import com.demo.top.model.dto.UserDTO;
import com.demo.top.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Artist> getFavouritesForUser(Long userId) {
        return repository.findById(userId)
                .orElseThrow(notFound(userId))
                .getFavourites();
    }

    public void saveFavourite(Long userId, FavoriteArtist favoriteArtist) {
        UserDTO user = repository.findById(userId)
                .orElseThrow(notFound(userId));

//        Artist artist = new Artist.ArtistBuilder()
//            .name(favoriteArtist.getArtistName())
//            .(favoriteArtist.getAmgArtistId())
//            .artistId(favoriteArtist.getArtistId())
//            .build();

//        user.getFavourites().add(artist);

        repository.save(user);
    }

    private Supplier<UserNotFoundException> notFound(Long id) {
        return () -> new UserNotFoundException(id);
    }

}
