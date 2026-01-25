package com.sbs.java.board;

import com.sbs.java.board.boundedContext.article.Article;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public List<Article> articles;
    public int lastArticleId;
    public Scanner sc;

    public App() {
        articles = new ArrayList<>();
        lastArticleId = 0;
        sc = Container.sc;
    }
    void makeArticleTestData( ) {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> articles.add(new Article(i, "제목"+i, "내용"+i) )  );
    }

    void run(){
        makeArticleTestData();

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
                actionUsrArticleWrite();
                lastArticleId++;
            }
            else if( rq.getUrlPath().equals("/usr/article/detail")){
                actionUsrArticleDetail(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/list")){
                actionUsrArticleList(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/modify")){
                actionUsrArticleModify(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/delete")){
                actionUsrArticleDelete(rq);
            }
            else {
                System.out.println("잘못된 명령어 입니다.");
            }
        }

        System.out.println("== 자바 게시판 종료 ==");

        sc.close();
    }

    private void actionUsrArticleDelete(Rq rq) {
        Map<String, String> params = rq.getParams();
        if( !params.containsKey("id") ) {
            System.out.println("id값을 입력해주세요.");
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(params.get("id"));
        } catch( NumberFormatException e ){
            System.out.println("id를 정수형태롤 입력해주세요.");
            return;
        }

        if( articles.isEmpty()){
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }
        if( id > articles.size() ){
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        Article article = findById(id, articles);

        if( article == null ){
            System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
            return;
        }
        articles.remove(article);
        System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id );
    }

    private void actionUsrArticleModify(Rq rq) {
        Map<String, String> params = rq.getParams();
        if( !params.containsKey("id") ) {
            System.out.println("id값을 입력해주세요.");
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(params.get("id"));
        } catch( NumberFormatException e ){
            System.out.println("id를 정수형태롤 입력해주세요.");
            return;
        }

        if( articles.isEmpty()){
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }
        if( id > articles.size() ){
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }
        System.out.printf("== %d번 게시물 수정 ==\n", id);
        Article article = findById(id, articles);

        if( article == null ){
            System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
            return;
        }

        System.out.print("새 제목 : ");
        article.subject = sc.nextLine();

        System.out.print("새 내용 : ");
        article.content = sc.nextLine();

        System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
    }

    private void actionUsrArticleWrite() {
        lastArticleId = articles.get(articles.size()-1).id;

        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = sc.nextLine();
        if( subject.trim().isEmpty() ){
            System.out.println("제목을 입력해주세요.");
            return;
        }

        System.out.print("내용 : ");
        String user = sc.nextLine();
        if( user.trim().isEmpty() ){
            System.out.println("내용을 입력해주세요.");
            return;
        }
        int id= ++lastArticleId;

        Article article = new Article(id, subject, user);
        articles.add(article);

        System.out.println("생성 된 게시물 객체 : " + article);
        System.out.printf("제목: %s \n", subject);
        System.out.printf("내용: %s \n", user );
        System.out.printf("%d번 게시물이 등록되었습니다.\n", id );
    }

    private void actionUsrArticleDetail(Rq rq) {
        Map<String, String> params = rq.getParams();
        if( !params.containsKey("id") ) {
            System.out.println("id값을 입력해주세요.");
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(params.get("id"));
        } catch( NumberFormatException e ){
            System.out.println("id를 정수형태롤 입력해주세요.");
            return;
        }

        if( articles.isEmpty()){
            System.out.println("게시물이 존재하지 않습니다.");
            return;
        }
        if( id > articles.size() ){
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }
        Article article = findById(id, articles);

        if( article == null ){
            System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("== %d번 게시물 상세보기 ==\n", id);
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.subject);
        System.out.printf("내용 : %s\n", article.content);

    }

    private void actionUsrArticleList(Rq rq) {
        Map<String, String>  params = rq.getParams();

        // 검색 시작
        List<Article> filteredArticles = new ArrayList<>(articles);
        if( params.containsKey("searchKeyword") ){
            String searchKeyword = params.get("searchKeyword");
            // filteredArticles = new ArrayList<>(); // 새 리스트 객체 생성
            filteredArticles = articles.stream()
                    .filter(article -> article.subject.contains(searchKeyword) || article.content.contains(searchKeyword))
                    .collect(Collectors.toList());
        }
        // 정렬 로직
        List<Article> sortedArticles = filteredArticles;
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
            return;
        }
        System.out.printf("== 게시물 리스트 (총 %d개)==\n", sortedArticles.size());
        System.out.println("번호 | 제목");

        sortedArticles.forEach(
                article -> System.out.printf("%d | %s\n", article.id, article.subject));
    }

    private Article findById(int id, List<Article> articles) {
        return articles.stream()
                .filter(article-> article.id == id )
                .findFirst()
                .orElse(null);
    }
}
