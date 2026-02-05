package com.sbs.java.board.container;

import com.sbs.java.board.boundedContext.article.controller.ArticleController;
import com.sbs.java.board.boundedContext.article.repository.ArticleRepository;
import com.sbs.java.board.boundedContext.article.service.ArticleService;
import com.sbs.java.board.boundedContext.member.controller.MemberController;

import java.util.Scanner;

public class Container {
    public static Scanner sc;

    // 1. ArticleRepository 먼저 생성.
    public static ArticleRepository articleRepository;
    // 2. ArticleService 를 먼저 만들고, 다음으로 ArticleController를 만들어야 된다.
    public static ArticleService articleService;

    public static MemberController memberController;
    public static ArticleController articleController;

    static {
        sc = new Scanner(System.in);
        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        memberController = new MemberController();
        articleController = new ArticleController();
    }
}
