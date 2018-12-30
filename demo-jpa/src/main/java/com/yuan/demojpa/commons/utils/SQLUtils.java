package com.yuan.demojpa.commons.utils;

import java.util.Collection;

public class SQLUtils {

    public static <T extends CharSequence> String generateSQL(T sql, Collection<T> collection) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        if (collection != null && collection.size() > 0) {
            stringBuilder.append(" where ");
            for (T t : collection) {
                stringBuilder.append(" ").append(t).append(" and ");
            }
            stringBuilder = new StringBuilder(stringBuilder.substring(stringBuilder.lastIndexOf("and")));
        }
        return stringBuilder.toString();
    }
}
