package com.yuan.demojpa.commons.utils;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


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


}
