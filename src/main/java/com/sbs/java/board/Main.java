package com.sbs.java.board;

import com.sbs.java.board.container.Container;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class Article {
    int id;
    String subject;
    String content;

    Article (int id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{id: %d, subject: '%s', content: '%s'}".formatted(id, subject, content);
    }
}

class Rq {
    String url;
    Map<String, String> params;
    String urlPath;

    Rq(String url){
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
class Util {
    static Map<String, String> getParamsFromUrl(String url){
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

    static String getPathFromUrl(String url){
        return url.split("\\?", 2)[0];
    }
}
