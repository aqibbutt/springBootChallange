package com.example.assignment.dto;

import com.example.assignment.Util.Common;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Data
public class PagingAndSorting {

    @NotNull
    private Integer pageNumber = Common.DEFAULT_PAGE_NUMBER;

    @NotNull
    private Integer pageSize = Common.DEFAULT_PAGE_SIZE;

    @NotNull
    private List<String> sortBy = Collections.singletonList(Common.DEFAULT_SORT_BY);

    @NotNull
    private List<String> sortDir = Collections.singletonList(Common.DEFAULT_SORT_DIRECTION);
}
