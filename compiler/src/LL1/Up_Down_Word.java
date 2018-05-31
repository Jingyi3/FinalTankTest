package LL1;

/**
 * Created by ZJYYY on 2018/5/3.
 */

import java.util.Scanner;

import static java.lang.System.exit;

public class Up_Down_Word {

    private static int index = 0;
    private static String str = new String();
    private static StringBuffer sb = new StringBuffer("");
    private static char[] chars = null;
    private static boolean flag = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        sb = sb.append(str);
        sb = sb.append("@");
        sb = sb.append("#");
        chars = sb.toString().toCharArray();

        E();
        if (flag) {
            System.out.println("正确");
        }

    }

    static void E() {
        // E ->TE’
        System.out.println("E ->TE’");
        T();
        E1();
    }

    static void E1() {
        // E’-> +TE’| ε
        //select(E'→ ε)=follow(E')={),#}

        if (chars[index] == '+') {
            System.out.println("E’-> +TE’");
            System.out.println("+匹配");
            index++;
            T();
            E1();
            if (isEnd()) {
                exit(0);
            }
        } else if (chars[index] == ')') {
            System.out.println("E'->ε");
        } else {
            flag = false;
            System.out.println("失败");
        }
    }

    static void T() {
        // T->FT’
        System.out.println("T->FT'");
        F();
        T1();
    }

    static void T1() {
        // T’->*FT’|ε
        //select(T'→ ε)=follow(T')={+,),#)

        if (chars[index] == '*') {
            System.out.println("T’->*FT’");
            System.out.println("*匹配");
            index++;
            F();
            T1();
            if (isEnd()) {
                exit(0);
            }
        } else if (chars[index] == '+' || chars[index] == ')') {
            System.out.println("T'->ε");
        } else {
            flag = false;
            System.out.println("失败");
        }
    }

    static void F() {
        // F ->(E)|i
        if (chars[index] == 'i') {
            System.out.println("F->i");
            System.out.println("i匹配");

            index++;
            if (isEnd()) {
                exit(0);
            }
        } else if (chars[index] == '(') {
            System.out.println("( 匹配");

            index++;
            E();
            if (chars[index] == ')') {
                System.out.println(") 匹配");

                index++;
                System.out.println("F->(E)");
                if (isEnd()) {
                    exit(0);
                }
            } else {
                flag = false;

                System.out.println("分析失败");
            }
        } else {
            flag = false;
            System.out.println("分析失败");
        }
    }

    static boolean isEnd() {
        boolean b = false;
        if (chars[index] == '#') {
            b = true;
        }
        return b;
    }
}
