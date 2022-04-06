package com.comebackhome.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositorySliceHelper {

    public static <T> Slice<T> toSlice(List<T> contents, Pageable pageable) {

        boolean hasNext = isContentSizeGreaterThanPageSize(contents, pageable);
        return new SliceImpl<>(hasNext ? subListLastContent(contents, pageable) : contents, pageable, hasNext);
    }

    private static <T> boolean isContentSizeGreaterThanPageSize(List<T> content, Pageable pageable) {
        return content.size() > pageable.getPageSize();
    }

    private static <T> List<T> subListLastContent(List<T> content, Pageable pageable) {
        return content.subList(0, pageable.getPageSize());
    }
}