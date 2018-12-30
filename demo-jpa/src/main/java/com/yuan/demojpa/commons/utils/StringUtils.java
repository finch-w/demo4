package com.yuan.demojpa.commons.utils;

public class StringUtils extends org.springframework.util.StringUtils {
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
