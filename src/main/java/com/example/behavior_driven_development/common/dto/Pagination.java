package com.example.behavior_driven_development.common.dto;

import jakarta.validation.constraints.NotNull;

public record Pagination(
        @NotNull
        long totalCount,

        @NotNull
        int totalPages,

        @NotNull
        int currPage,

        @NotNull
        int prevPage,

        @NotNull
        int nextPage,

        @NotNull
        boolean firstPage,

        @NotNull
        boolean lastPage
) {
        public Pagination(long totalCount, int totalPages, int page, boolean firstPage, boolean lastPage) {
                this(
                        totalCount,
                        totalPages,
                        page + 1,
                        firstPage ? page : page - 1,
                        lastPage ? page : page + 1,
                        firstPage,
                        lastPage
                );
        }
}
