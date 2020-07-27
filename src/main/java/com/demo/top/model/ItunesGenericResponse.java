package com.demo.top.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ItunesGenericResponse<RESPONSE> {
    private Integer resultCount;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private List<RESPONSE> results;

    public List<RESPONSE> getResults() {
        return results;
    }

    public void setResults(List<RESPONSE> results) {
        this.results = results;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
}
