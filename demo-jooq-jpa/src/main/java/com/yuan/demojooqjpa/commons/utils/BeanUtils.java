package com.yuan.demojooqjpa.commons.utils;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class BeanUtils extends org.springframework.beans.BeanUtils {
    public static <T> void copyPojo(T source, T target) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        Set<String> prorpeties = new HashSet<>();
        for (PropertyDescriptor descriptor : beanWrapper.getPropertyDescriptors()) {
            String name = descriptor.getName();
            Object value = beanWrapper.getPropertyValue(name);
            if (StringUtils.isEmpty(value)) {
                prorpeties.add(name);
            }
        }
        copyProperties(source, target, prorpeties.toArray(new String[prorpeties.size()]));
    }
}
