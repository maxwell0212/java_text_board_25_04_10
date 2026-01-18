import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String url = "/usr/article/write?subject=자바는 무엇인가요?&content=자바는 객체지향 프로그래밍 언어입니다.1+2=3&writerName=오애순&boardId=1&id=";

        Rq rq = new Rq(url);
        Map<String, String> params = rq.getParams();
        System.out.println(params);

        String urlPath = rq.getUrlPath();
        System.out.println(urlPath);

    }

}

class Rq {
    String url;
    Map<String, String> params;
    String urlPath;

    Rq(String url){
        this.url = url;
    }

    public Map<String, String> getParams() {
        if(this.params == null) {
            this.params = Util.getParamsFromUrl(this.url);
        }
        return this.params;
    }

    public String getUrlPath() {
        if( this.urlPath == null) {
            this.urlPath = Util.getPathFromUrl(this.url);
        }
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
