package com.sbs.java.board.global.base;

import com.sbs.java.board.global.util.Util;

import java.util.Map;

public class Rq {
    String url;
    Map<String, String> params;
    String urlPath;

    public Rq(String url){
        this.url = url;
        this.params = Util.getParamsFromUrl(url);
        this.urlPath = Util.getPathFromUrl(url);
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public String getUrlPath() {
        return this.urlPath;
    }
}