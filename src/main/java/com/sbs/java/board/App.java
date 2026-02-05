package com.sbs.java.board;

import com.sbs.java.board.boundedContext.article.controller.ArticleController;
import com.sbs.java.board.boundedContext.article.dto.Article;
import com.sbs.java.board.boundedContext.member.controller.MemberController;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public ArticleController articleController;
    public MemberController memberController;

    public App() {
        articleController = Container.articleController;
        memberController = Container.memberController;
    }


    void run(){
        System.out.println("== 자바 게시판 ==");
        System.out.println("게시판을 시작합니다.");

        while(true){
            System.out.print("명령) ");
            String cmd = Container.sc.nextLine();
            Rq rq = new Rq(cmd);
            if( rq.getUrlPath().equals("exit")){
                System.out.println("게시판을 종료합니다.");
                break;
            }
            else if( rq.getUrlPath().equals("/usr/article/write")){
                articleController.doWrite();
            }
            else if( rq.getUrlPath().equals("/usr/article/detail")){
                articleController.showDetail(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/list")){
                articleController.showList(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/modify")){
                articleController.doModify(rq);
            }
            else if( rq.getUrlPath().equals("/usr/article/delete")){
                articleController.doDelete(rq);
            }
            else if( rq.getUrlPath().equals("/usr/member/join")){
                memberController.doJoin(rq);
            }
            else {
                System.out.println("잘못된 명령어 입니다.");
            }
        }

        System.out.println("== 자바 게시판 종료 ==");

        Container.sc.close();
    }
}
