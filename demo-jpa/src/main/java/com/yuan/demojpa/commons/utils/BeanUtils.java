package com.yuan.demojpa.commons.utils;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;


public class BeanUtils extends org.springframework.beans.BeanUtils {
    public static <T> void copyPojo(T source, T target) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        Set<String> prorpeties = new HashSet<>();
        Arrays.stream(beanWrapper.getPropertyDescriptors()).map(FeatureDescriptor::getName).forEachOrdered(name -> {
            Object value = beanWrapper.getPropertyValue(name);
            if (StringUtils.isEmpty(value)) {
                prorpeties.add(name);
            }
        });
        copyProperties(source, target, prorpeties.toArray(new String[prorpeties.size()]));
    }

    public static <T> T mapToBean(Map map, Class<T> type) {
        try {
            if (map != null && map.size() > 0) {
                T t = type.newInstance();
                BeanWrapperImpl beanWrapper = new BeanWrapperImpl(t);
                PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String name = propertyDescriptor.getName();
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (writeMethod != null) {
                        writeMethod.invoke(t, map.get(name));
                    }
                }
                return t;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> beanToMap(Object object) {
        try {
            if (object == null) {
                return null;
            } else {
                Map<String, Object> map = new HashMap<>();
                BeanWrapperImpl beanWrapper = new BeanWrapperImpl(object);
                PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String name = propertyDescriptor.getName();
                    if (name.equals("class")) {
                        continue;
                    } else {
                        Method readMethod = propertyDescriptor.getReadMethod();
                        Object value = readMethod.invoke(object);
                        if (!StringUtils.isEmpty(value)) {
                            map.put(name, object);
                        }
                    }
                }
                return map;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
