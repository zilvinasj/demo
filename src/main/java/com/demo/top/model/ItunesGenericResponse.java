package com.demo.top.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItunesGenericResponse<RESPONSE> {
    private Integer resultCount;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private List<RESPONSE> results;
}
