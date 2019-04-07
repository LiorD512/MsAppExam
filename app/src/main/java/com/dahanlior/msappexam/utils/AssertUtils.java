package com.dahanlior.msappexam.utils;


public class AssertUtils {

    public static void assertNotNull(Object object) {
        if(object == null) throw new IllegalArgumentException("Can not be null");
    }
}
