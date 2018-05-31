package LL1;

import java.util.Scanner;

/**
 * Created by ZJYYY on 2018/5/3.
 */
public class LL1 {
    static Scanner reader = new Scanner(System.in);
    static String input;

    public static void main(String[] args) {


        E();


        input = reader.next();
//
//        if(ip == input.length-1)//说明输入串已经分析完
//            System.out.println("分析结果：符合文法");
//        else//输入串没有分析完就结束
//            System.out.println("分析结果：ERROR  不符合文法");
    }

    static void E() {
        System.out.println("E ");
        T();
        E1();

    }

    static void T(){
        System.out.println("T ");
    }

    static void E1() {
        System.out.println("E1 ");
        if (input.equals("=")) {

        }

    }

    static void F(){
        System.out.println("F ");

    }

    static void T1(){
        System.out.println("T1 ");


    }

//    public static void E()
//    {
//        T();
//        while(sym == '+')
//        {
//            ip++;
//            sym = input[ip];//advance
//            T();
//        }
//    }
//
//    public static void T()
//    {
//        F();
//        while(sym == '*')
//        {
//            ip++;
//            sym = input[ip];//advance
//            F();
//        }
//    }
//
//    public static void F()
//    {
//        if(sym == 'i')
//        {
//            ip++;
//            sym = input[ip];//advance
//        }
//        else
//        {
//            if(sym == '(')
//            {
//                ip++;
//                sym = input[ip];//advance
//
//                E();
//
//                if(sym == ')')
//                {
//                    ip++;
//                    sym = input[ip];//advance
//                }
//                else
//                {
//                    System.out.println("分析结果：ERROR  不符合文法");
//                    System.exit(0);
//                }
//            }
//            else
//            {
//                System.out.println("分析结果：ERROR  不符合文法");
//                System.exit(0);
//            }
//        }
//    }
}
