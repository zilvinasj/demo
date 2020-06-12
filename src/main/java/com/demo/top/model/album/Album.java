package com.demo.top.model.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Album {

    private String collectionName;

    private Long collectionId;

    private URL artworkUrl60;
}
