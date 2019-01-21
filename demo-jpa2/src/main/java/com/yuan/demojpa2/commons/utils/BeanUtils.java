package com.yuan.demojpa2.commons.utils;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.*;

public class BeanUtils {

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    public static <T> void copyPojo(T source, T target) {
        BeanWrapperImpl sourceBeanWrapper = new BeanWrapperImpl(source);
        List<String> list = new ArrayList<>();
        PropertyDescriptor[] descriptors = sourceBeanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            Object value = sourceBeanWrapper.getPropertyValue(name);
            if (StringUtils.isEmpty(value)) {
                list.add(name);
            }
        }
        org.springframework.beans.BeanUtils.copyProperties(source, target, list.toArray(new String[list.size()]));
    }

    public static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(object);
        PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            Object value = beanWrapper.getPropertyValue(name);
            map.put(name, value);
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> type) {
        T t = org.springframework.beans.BeanUtils.instantiateClass(type);
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(t);
        PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            beanWrapper.setPropertyValue(entry.getKey(), entry.getValue());
        }
        return t;
    }
}
