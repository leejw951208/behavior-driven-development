package com.example.behavior_driven_development.common.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.List;

public record PagingDto<T>(
        @NotNull
        List<T> content,

        @NotNull
        Pagination pagination
) {
    public PagingDto(Page<T> page) {
        this(page.getContent(), new Pagination(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.isFirst(), page.isLast()));
    }
}
