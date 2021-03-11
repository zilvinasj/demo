package com.demo.top.service.album;

import com.demo.top.model.album.Album;
import java.util.List;

public interface AlbumService {
  List<Album> getAlbumsByName(String name);
}
