package com.sbs.java.board.global.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
    public static Map<String, String> getParamsFromUrl(String url){
        Map<String, String> params = new LinkedHashMap<>();

        String[] urlBits = url.split("\\?", 2);
        if( urlBits.length == 1)
            return params;
        String queryStr = urlBits[1];
        for(String bit : queryStr.split("&") ) {
            String[] bits = bit.split("=", 2);
            //System.out.println(Arrays.toString(bits));
            //System.out.printf("length: %d \n", bits.length);
            if(bits[1].isEmpty()) continue;
            params.put(bits[0], bits[1]);
        }

        return params;
    }

    public static String getPathFromUrl(String url){
        return url.split("\\?", 2)[0];
    }
}

