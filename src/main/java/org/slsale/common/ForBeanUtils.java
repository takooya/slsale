package org.slsale.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:09 2018/3/24
 */
public class ForBeanUtils {
    public static String[] getNullPropertiesName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] strings = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true); // 设置些属性是可以访问的
            try {
                if (fields[i].get(obj) == null) {
                    strings[i] = fields[i].getName();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }
    public static String[] getNotNullPropertiesName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] strings = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true); // 设置些属性是可以访问的
            try {
                if (fields[i].get(obj) != null) {
                    strings[i] = fields[i].getName();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

    public static Map<String,String> getProperties(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String,String> properties = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true); // 设置些属性是可以访问的
            try {
                if(fields[i].get(obj)!=null){
                    properties.put(fields[i].getName(),fields[i].get(obj).toString());
                }else {
                    properties.put(fields[i].getName(),null);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
