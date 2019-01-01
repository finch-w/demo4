package com.yuan.demomybatis2.commons.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class BeanUtils extends org.springframework.beans.BeanUtils {
    public static Object convert(Object object, String properties) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(object);
        return ConvertUtils.convert(beanWrapper.getPropertyValue(properties), beanWrapper.getPropertyType(properties));
    }

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
