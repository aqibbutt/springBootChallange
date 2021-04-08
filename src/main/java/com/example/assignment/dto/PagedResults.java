package com.example.assignment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class PagedResults<T> {

    @NonNull
    private Long totalPages;

    @NonNull
    private Long totalResults;

    @NonNull
    private List<T> data;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PagedResults(@NonNull Long totalPages, @NonNull Long totalResults, @NonNull List<T> data) {
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.data = data;
    }
}
