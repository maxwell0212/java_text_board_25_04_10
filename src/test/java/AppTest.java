import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "subject=제목1&content=내용1&writerName=양관식&boardId=5";
        String[] queryStringBits = queryString.split("&");
        System.out.println(Arrays.toString(queryStringBits) );

        Map<String, String> params = new HashMap<>();

        for(String bit : queryStringBits) {
            String[] bitBits = bit.split("=");

            String paramName = bitBits[0];
            String paramValue = bitBits[1];

            params.put(paramName, paramValue);
            //System.out.printf("%s : %s\n", paramName, paramValue );
        }
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
