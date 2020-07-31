package com.demo.top.controller;

import com.demo.top.domain.artist.FavoriteArtist;
import com.demo.top.model.dto.UserDTO;
import com.demo.top.model.response.ApplicationArtistResponse;
import com.demo.top.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{userId}/artists/favourites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationArtistResponse> getFavouriteArtists(@PathVariable Long userId) {

        ApplicationArtistResponse response = new ApplicationArtistResponse();
        response.setArtists(userService.getFavouritesForUser(userId));

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "{userId}/artists/favourites", produces = MediaType.APPLICATION_JSON_VALUE)
    // Shouldn't use a persistent entity here, but doing it for simplicity
    public ResponseEntity<Void> saveArtistAsFavourite(@PathVariable Long userId, @RequestBody @Valid FavoriteArtist favoriteArtist) {
        userService.saveFavourite(userId, favoriteArtist);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    // Shouldn't use a persistent entity here, but doing it for simplicity
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO user) {

        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }

}
