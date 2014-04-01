package br.com.banav.util;

import java.util.HashMap;

/**
 * Created by GilsonRocha on 27/12/13.
 */
public class Session {

    protected static HashMap<String, Object> data;

    static {
        data = new HashMap<String, Object>(10);
    }

    public static Object get(String key) {
        return data.get(key);
    }

    public static void put(String key, Object value) {
        data.put(key, value);
    }
}