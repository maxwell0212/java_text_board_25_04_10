package com.sbs.java.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static void article_test_data(List<Article> articles) {
        /*
        articles.add(new Article(1, "제목1", "내용1"));
        articles.add(new Article(2, "제목2", "내용2"));
        articles.add(new Article(3, "제목3", "내용3"));
        */
        IntStream.rangeClosed(1, 3)
                .forEach(i -> articles.add(new Article(i, "제목"+i, "내용"+i) )  );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;
        Article lastArticle = null;
        List<Article> articles = new ArrayList<>();
        article_test_data(articles);

        System.out.println("== 자바 게시판 ==");
        System.out.println("게시판을 시작합니다.");

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();
            if( cmd.equals("exit")){
                System.out.println("게시판을 종료합니다.");
                break;
            }
            else if( cmd.equals("/usr/article/write")){
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
                lastArticle = article;

                System.out.println("생성 된 게시물 객체 : " + article);
                System.out.printf("제목: %s \n", subject);
                System.out.printf("내용: %s \n", user );
                System.out.printf("%d번 게시물이 등록되었습니다.\n", id );
            }
            else if( cmd.equals("/usr/article/detail")){
                Article article = lastArticle;
                if( article == null ){
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }
                System.out.println("== 게시물 상세보기 ==");
                System.out.printf("번호 : %d\n", article.id);
                System.out.printf("제목 : %s\n", article.subject);
                System.out.printf("내용 : %s\n", article.content);

            }
            else if( cmd.equals("/usr/article/list")){
                if( articles.isEmpty() ){
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }
                System.out.println("== 게시물 리스트 ==");
                System.out.println("번호 | 제목");
                /*
                for(int i=0; i<articles.size(); i++){
                    Article article = articles.get(i);
                    System.out.printf("%d | %s\n", article.id, article.subject);
                }
                */
                /*
                for( Article article : articles){
                    System.out.printf("%d | %s\n", article.id, article.subject);
                }
                */
                articles.forEach( article ->
                        System.out.printf("%d | %s\n", article.id, article.subject));
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
