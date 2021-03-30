package com.demo.top.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class SpotifyGenericResponse<RESPONSE> {
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private List<RESPONSE> items;

    public List<RESPONSE> getItems() {
        return items;
    }

    public void setItems(List<RESPONSE> items) {
        this.items = items;
    }
}
