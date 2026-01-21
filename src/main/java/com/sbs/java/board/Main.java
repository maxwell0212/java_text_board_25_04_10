package com.sbs.java.board;

import java.util.*;
import java.util.stream.IntStream;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static void makeArticleTestData(List<Article> articles) {
        /*
        articles.add(new Article(1, "제목1", "내용1"));
        articles.add(new Article(2, "제목2", "내용2"));
        articles.add(new Article(3, "제목3", "내용3"));
        */
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목"+i, "내용"+i) )  );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;

        List<Article> articles = new ArrayList<>();
        makeArticleTestData(articles);
        lastArticleId = articles.get(articles.size()-1).id;

        System.out.println("== 자바 게시판 ==");
        System.out.println("게시판을 시작합니다.");

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();
            Rq rq = new Rq(cmd);
            if( rq.getUrlPath().equals("exit")){
                System.out.println("게시판을 종료합니다.");
                break;
            }
            else if( rq.getUrlPath().equals("/usr/article/write")){
                System.out.println("== 게시물 작성 ==");
                System.out.print("제목 : ");
                String subject = sc.nextLine();
                if( subject.trim().isEmpty() ){
                    System.out.println("제목을 입력해주세요.");
                    continue;
                }

                System.out.print("내용 : ");
                String user = sc.nextLine();
                if( user.trim().isEmpty() ){
                    System.out.println("내용을 입력해주세요.");
                    continue;
                }
                int id= ++lastArticleId;

                Article article = new Article(id, subject, user);
                articles.add(article);

                System.out.println("생성 된 게시물 객체 : " + article);
                System.out.printf("제목: %s \n", subject);
                System.out.printf("내용: %s \n", user );
                System.out.printf("%d번 게시물이 등록되었습니다.\n", id );
            }
            else if( rq.getUrlPath().equals("/usr/article/detail")){
                Map<String, String> params = rq.getParams();
                if( !params.containsKey("id") ) {
                    System.out.println("id값을 입력해주세요.");
                    continue;
                }
                int id = 0;
                try{
                    id = Integer.parseInt(params.get("id"));
                } catch( NumberFormatException e ){
                    System.out.println("id를 정수형태롤 입력해주세요.");
                    continue;
                }

                if( articles.isEmpty()){
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }
                if( id > articles.size() ){
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }
                Article article = articles.get(id-1);

                System.out.println("== 게시물 상세보기 ==");
                System.out.printf("번호 : %d\n", article.id);
                System.out.printf("제목 : %s\n", article.subject);
                System.out.printf("내용 : %s\n", article.content);

            }
            else if( rq.getUrlPath().equals("/usr/article/list")){
                Map<String, String>  params = rq.getParams();
                List<Article> sortedArticles = new ArrayList<>(articles);
                boolean orderByIdDesc = true;

                if( params.containsKey("orderBy") ){
                    String orderBy = params.get("orderBy");
                    switch (orderBy){
                        case "idAsc":
                            sortedArticles.sort((a1, a2) -> a1.id - a2.id);
                            break;
                        case "idDesc":
                        default:
                            sortedArticles.sort((a1, a2) -> a2.id - a1.id);
                            break;
                    }
                }
                else {
                    sortedArticles.sort( (a1, a2) -> a2.id - a1.id );
                }

                if( articles.isEmpty() ){
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }
                System.out.println("== 게시물 리스트 ==");
                System.out.println("번호 | 제목");

                sortedArticles.forEach(
                            article -> System.out.printf("%d | %s\n", article.id, article.subject));
            }
            else {
                System.out.println("잘못된 명령어 입니다.");
            }
        }

        System.out.println("== 자바 게시판 종료 ==");

        sc.close();
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
