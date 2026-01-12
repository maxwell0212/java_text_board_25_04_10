package com.sbs.java.board;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
                String article = sc.nextLine();
                System.out.print("내용 : ");
                String user = sc.nextLine();

                int id= 1;
                System.out.printf("제목: %s \n", article);
                System.out.printf("내용: %s \n", user );
                System.out.printf("%d번 게시물이 등록되었습니다.\n", id );
            }
            else {
                System.out.println("잘못된 명령어 입니다.");
            }

        }

        System.out.println("== 자바 게시판 종료 ==");

        sc.close();
    }
}