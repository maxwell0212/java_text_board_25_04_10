import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "subject=제목1&content=내용1&writerName=양관식&boardId=5";
        Map<String, String> params = Util.getParams(queryString);
        System.out.println(params);

        queryString = "subject=제목20&content=내용20&writerName=오애순&boardId=6";
        params = Util.getParams(queryString);
        System.out.println(params);

        System.out.println("== 원하는 것만 하나씩 뽑아오기 ==");
        System.out.printf("subject : %s\n", params.get("subject"));
        System.out.printf("content : %s\n", params.get("content"));
        System.out.printf("writerName : %s\n", params.get("writerName"));
        System.out.printf("boardId : %s\n", params.get("boardId"));

        System.out.println("== 반복문을 이용한 데이터 순회 ==");
        params.forEach((key, value)-> System.out.printf("%s : %s\n", key, value));
    }

}

class Util {
    static Map<String, String> getParams(String queryStr){
        Map<String, String> params = new LinkedHashMap<>();

        String[] queryString = queryStr.split("&");

        for(String bit : queryString ) {
            String[] bitBits = bit.split("=");
            params.put(bitBits[0], bitBits[1]);
        }

        return params;
    }
}
