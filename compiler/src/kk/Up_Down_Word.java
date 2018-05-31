package kk;


import java.util.Scanner;

import static java.lang.System.exit;

public class Up_Down_Word {

    private static int index = 0;
    private static String str = new String();
    private static StringBuffer sb = new StringBuffer("");
    private static char[] chars = null;

    static void E() {
        // E ->TE’
        System.out.println("E ->TE’");
        T();
        E1();
    }

    static void E1() {
        // E’-> +TE’| ε  select(E'→ ε)={),#}
        if (chars[index] == '+') {
            System.out.println("E’-> +TE’");
            System.out.println("+匹配");
            index++;
            T();
            E1();
            if (isEnd()) {
                exit(0);
            }
        } else if (chars[index] != ')') {
            System.out.println("失败");
            exit(0);
        }
    }

    static void T() {
        // T->FT’
        System.out.println("T->FT'");
        F();
        T1();
    }

    static void T1() {
        // T’->*FT’|ε  select(T'→ ε)={+,),#)
        if (chars[index] == '*') {
            System.out.println("T’->*FT’");
            System.out.println("*匹配");
            index++;
            F();
            T1();
            if (isEnd()) {
                exit(0);
            }
        }else if (chars[index] != ')' || chars[index] != '+') {
            System.out.println("失败");
            exit(0);
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
            System.out.println("（匹配");
            index++;
            E();
            if (chars[index] == ')') {
                System.out.println("F->(E)");
                System.out.println("）匹配");
                index++;
                if (isEnd()) {
                    exit(0);
                }
            } else {
                System.out.println("分析失败");
            }
        } else {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        sb = sb.append(str);
        sb = sb.append("@");
        sb = sb.append("#");
        chars = sb.toString().toCharArray();
        E();
    }
}
