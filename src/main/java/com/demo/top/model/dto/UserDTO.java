package com.demo.top.model.dto;

import com.demo.top.model.artist.Artist;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> favourites;

    @OneToMany
    private List<PlaylistDTO> playlists;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Artist> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Artist> favourites) {
        this.favourites = favourites;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
