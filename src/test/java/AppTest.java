import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String url = "/usr/article/write?subject=자바는 무엇인가요?&content=자바는 객체지향 프로그래밍 언어입니다.1+2=3&writerName=오애순&boardId=1&id=";
        Map<String, String> params = Util.getParamsFromUrl(url);
        System.out.println(params);

        System.out.println("== 원하는 것만 하나씩 뽑아오기 ==");
        System.out.printf("subject : %s\n", params.get("subject"));
        System.out.printf("content : %s\n", params.get("content"));
        System.out.printf("writerName : %s\n", params.get("writerName"));
        System.out.printf("boardId : %s\n", params.get("boardId"));

        System.out.println("== 반복문을 이용한 데이터 순회 ==");
        params.forEach((key, value)-> System.out.printf("%s : %s\n", key, value));

        String urlPath = Util.getPathFromUrl(url);
        System.out.println(urlPath);
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
            System.out.println(Arrays.toString(bits));
            System.out.printf("length: %d \n", bits.length);
            if(bits[1].isEmpty()) continue;
            params.put(bits[0], bits[1]);
        }

        return params;
    }

    static String getPathFromUrl(String url){
        return url.split("\\?", 2)[0];
    }
}
