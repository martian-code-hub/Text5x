package com.example.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yangpei on 2016/12/1.
 */

public class MapTraverse {

    /**
     * 方法1
     */
    public static void traverse1(){
        Map<String, String> map = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    /**
     * 方法2
     */
    public static void traverse2(){
        Map<String, String> map = new HashMap<String, String>();

        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 方法3
     */
    public static void traverse3(){
        Map<String, String> map = new HashMap<String, String>();

       for(String key:map.keySet()){
           System.out.println("key:" + key);
       }
        for(String value:map.values()){
            System.out.println("value:" + value);
        }
    }

    /**
     * 方法4
     */
    public static void traverse4(){
        Map<String, String> map = new HashMap<String, String>();

        for(String key:map.keySet()){
            String value = map.get(key);
            System.out.println("key:" + key+":"+"value:" + value);
        }

    }
}
