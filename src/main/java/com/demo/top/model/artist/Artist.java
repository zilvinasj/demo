package com.demo.top.model.artist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist {
    private String artistName;
    // Might be unnecessary
    private Long artistId;

    @Id
    private Long amgArtistId;
}
