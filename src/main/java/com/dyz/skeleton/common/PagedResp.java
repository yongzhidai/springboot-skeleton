package com.dyz.skeleton.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PagedResp<T> {
    private long total;
    private List<T> list;
}
