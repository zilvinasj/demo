package com.demo.top.model.dto;

import com.demo.top.model.artist.Artist;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> favourites;

    private String userName;


}
