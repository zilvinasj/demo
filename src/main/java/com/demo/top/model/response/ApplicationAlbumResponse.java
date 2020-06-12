package com.demo.top.model.response;

import com.demo.top.model.album.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationAlbumResponse {
    private List<Album> albums;
}
