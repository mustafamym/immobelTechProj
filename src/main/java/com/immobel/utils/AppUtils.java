package com.immobel.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class AppUtils {
    public static Pageable getPageableInfo(Integer currentPage, Integer pageSize, String sortedBy) {
        Pageable pageable;

        if (StringUtils.isBlank(sortedBy)) {
            pageable = PageRequest.of(currentPage, pageSize, Sort.by("createDate").descending());

        } else {
            pageable = PageRequest.of(currentPage, pageSize, Sort.by(sortedBy).descending());
        }
        return pageable;
    }
}
