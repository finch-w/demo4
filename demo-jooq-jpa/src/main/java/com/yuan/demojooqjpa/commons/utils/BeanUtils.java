package com.yuan.demojooqjpa.commons.utils;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeanUtils extends org.springframework.beans.BeanUtils {
    public enum IgnoreMode {
        /**
         * 排除空值
         */
        NULL,
        /**
         * 排除相同值
         */
        SAME,
        /**
         * 排除空值与相同值
         */
        SAMENULL,
        /**
         * 不排除
         */
        NOIGNORE
    }

    public static <T> void copyPojo(T source, T target, IgnoreMode mode) {
        BeanWrapperImpl sourceWrapper = new BeanWrapperImpl(source);
        BeanWrapperImpl targetWrapper = new BeanWrapperImpl(target);
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : sourceWrapper.getPropertyDescriptors()) {
            switch (mode) {
                case NOIGNORE:
                    properties = new HashSet<>();
                    break;
                case NULL:
                    String name = propertyDescriptor.getName();
                    Object value = propertyDescriptor.getValue(name);
                    if (value == null || value.toString().equals("")) {
                        properties.add(name);
                    }
                    break;
                case SAME:
                    name = propertyDescriptor.getName();
                    value = propertyDescriptor.getValue(name);
                    if (value.equals(targetWrapper.getPropertyValue(name))) {
                        properties.add(name);
                    }
                    break;
                case SAMENULL:
                    name = propertyDescriptor.getName();
                    value = propertyDescriptor.getValue(name);
                    if (value == null || value.toString().equals("") || value.equals(targetWrapper.getPropertyValue(name))) {
                        properties.add(name);
                    }
                    break;
            }
        }
        copyProperties(source, target, properties.toArray(new String[properties.size()]));
    }

    public static Boolean isNotEmpty(Object object) {
        return (Arrays.stream(new BeanWrapperImpl(object).getPropertyDescriptors()).filter(propertyDescriptor -> {
            return !StringUtils.isEmpty(propertyDescriptor.getValue(propertyDescriptor.getName()));
        }).toArray().length > 0);

    }

    public static class StringUtils extends org.springframework.util.StringUtils{
        public static boolean isNotEmpty(Object object){
            return !isEmpty(object);
        }
    }
}
