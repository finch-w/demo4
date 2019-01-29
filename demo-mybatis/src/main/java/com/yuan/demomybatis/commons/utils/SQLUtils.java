package com.yuan.demomybatis.commons.utils;

import java.util.Collection;

public class SQLUtils {
    public static <T extends CharSequence> String createSQL(T sql, Collection<T> collections) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        if (collections != null && collections.size() > 0) {
            stringBuilder.append(" where ");
            for (T collection : collections) {
                stringBuilder.append(" " + collection + " and ");
            }
            stringBuilder = new StringBuilder(stringBuilder.substring(stringBuilder.lastIndexOf("and")).trim());
        }
        return stringBuilder.toString();
    }

    public static <T extends CharSequence> String createSQL(T sql, Collection<T> collection, T other) {
        return String.format("%s %s", createSQL(sql, collection), other);
    }
}
