package com.demo.top.model.response;

import com.demo.top.model.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationArtistResponse {
    private List<Artist> artists;
}
