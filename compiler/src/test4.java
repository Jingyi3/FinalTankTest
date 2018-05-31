import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by thinkpad on 2018/5/24.
 */
public class test4 {

    private static Scanner scanner;
    private static String line;  //输入字符串
    private static String lookahead;//当前扫描的输入符号

    public static void main(String[] args) {
        test4 test4 = new test4();
    }

    test4() {
        System.out.println("表达式：");
        scanner = new Scanner(System.in);
        line = scanner.nextLine();
        //line输入字符串
//        line = trim(line);
//        line = lower(line);
        line = line + '#';
        char s[] = line.toCharArray();
        if (s[0] == '+' || s[0] == '-') {
            line = "0" + line;
        }
        lookahead = getToken();
        //lookahead当前扫描的输入符号
        ParseS();
    }

    private void matchToken(String a) {
        // 用于判别正在处理的终结符与当前的输入符号是否匹配，
        // 若匹配，则度取消一个符号，继续分析过程；
        // 否则，报告语法错误，并退出
        if (!lookahead.equals(a)) {
            System.out.println("Error1");
            exit(0);
        } else {
            lookahead = getToken();
            //匹配，向词法分析程序申请并读入下一个输入符号
        }
    }

    private void ParseS() {
        //S → E {print(E.val)}
//        System.out.println("S1");
        float Ev = ParseE();  //进入非终结符E的产生式
        System.out.println("Result is: " + Ev);  //Ev对应属性E.val
    }

    private float ParseE() {
        //E → T   {R.i:=T.val}  R   {E.val:=R.s}
        float Ev = -100;
        if (isNum(lookahead) || lookahead.equals("(")) {
            //如果当前扫描单词是数字或者(
            float Tv = ParseT(); //变量Tv对应属性T.val
            float Ri = Tv;  //变量Ri对应属性R.i
            float Rs = ParseR(Ri); //变量Rs对应属性R.s
            Ev = Rs;
        } else {
            System.out.println("Error2");
            exit(0);
        }
        return Ev;
    }

    private float ParseR(float ri) {
        //  R → +T {R1.i:=R.i+T.val}  R1  {R.s:=R1.s}
        //  R → -T {R1.i:=R.i-T.val} R1 {R.s:=R1.s}
        //  R → ε {R.s:=R.i}
        float Rs = -100;
        if (lookahead.equals("+")) {
            matchToken(lookahead);
            /// /用于判别正在处理的终结符与当前的输入符号是否匹配,往下读
            float Tv = ParseT();
            float R1i = ri + Tv;
            float R1s = ParseR(R1i);
            Rs = R1s;
//            System.out.println("Rs=" + Rs);
        } else if (lookahead.equals("-")) {
            matchToken(lookahead);
            float Tv = ParseT();
            float R1i = ri - Tv;
            float R1s = ParseR(R1i);
            Rs = R1s;
//            System.out.println("Rs=" + Rs);
        } else if (lookahead.equals(")") || lookahead.equals("#")) {
            Rs = ri;
        } else {
            System.out.println("Error3");
            exit(0);
        }
        return Rs;
    }

    private float ParseT() {
        //T → F {P.i:=F.val} P  {T.val:=P.s}
        float Tv = -100;
        if (isNum(lookahead) || lookahead.equals(")")) {
            float Fv = ParseF();
            float Pi = Fv;
            float Ps = ParseP(Pi);
            Tv = Ps;
//            System.out.println("Tv=" + Tv);
        } else {
            System.out.println("Error4");
            exit(0);
        }
        return Tv;
    }

    private float ParseP(float pi) {
        //  P → * F  {P1.i:=Pi*(F.val)}   P   {T.val:=P1.s}
        //  P → / F  {P1.i:=Pi/F.val}  P  {T.val:=P1.s}
        //  P → ε {P.s:=P.i}
        //System.out.println("P");
        float Ps = -100;
        if (lookahead.equals("*")) {
            matchToken(lookahead);
            float Fv = ParseF();
            float P1i = pi * Fv;
            float P1s = ParseP(P1i);
            Ps = P1s;
//            System.out.println("Ps=" + Ps);
        } else if (lookahead.equals("/")) {
            matchToken(lookahead);
            float Fv = ParseF();
            if (Fv == 0) {
                System.out.println("除数： 0");
            }
            float P1i = pi / Fv;
            float P1s = ParseP(P1i);
            Ps = P1s;
//            System.out.println("Ps=" + Ps);
        } else if (lookahead.equals(")") || lookahead.equals("#")
                || lookahead.equals("-") || lookahead.equals("+")) {
            Ps = pi;
        } else {
            System.out.println("Error4");
            exit(0);
        }
        return Ps;
    }

    private float ParseF() {
        //  F → (E)   {F.val:=E.val}
        //  F → d     {F.val:=d.lexval}
        float Fv = -100;
        if (isNum(lookahead)) {
            Fv = Float.parseFloat(lookahead);
            lookahead = getToken();
        } else if (lookahead.equals("(")) {
            matchToken("(");
            float Ev = ParseE();
            Fv = Ev;
            if (lookahead.equals(")")) {
                matchToken(")");
            }
        } else {
            System.out.println("Error5");
            exit(0);
        }
//        System.out.println("Fv=" + Fv);
        return Fv;
    }

    private String getToken() {
        //获取下一个标识符
        StringBuffer word = new StringBuffer("");
        char[] s = line.toCharArray();
        String finish = "";
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                continue;
            } else if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || s[i] == '=' ||
                    s[i] == '#' || s[i] == '(' || s[i] == ')' || s[i] == ',' || s[i] == ';' || s[i] == '.') {
                finish = s[i] + "";
            } else {
                if (s[i] == '<' || s[i] == '>' || s[i] == ':') {
                    if (s[i] == '<' && s[i + 1] == '=') {finish = "<=";
                    } else if (s[i] == '>' && s[i + 1] == '=') {finish = ">=";
                    } else if (s[i] == ':' && s[i + 1] == '=') {finish = ":=";
                    } else if (s[i] == ':') {System.out.println(": must has");
                    } else {String str = new String(s, i, s.length - i);
                        finish = str;}
                } else if (s[i] >= '0' && s[i] <= '9' || s[i] == '.') {
                    int j = 0;
                    int count = 0;
                    for (j = 0; (s[i] >= '0' && s[i] <= '9' || s[i] == '.'); ) {
                        if (s[i] == '.') {count++;}
                        word.append(s[i++]);}
                    if (count > 1) {System.out.println("no two:");
                        exit(0);}
                    finish = word.toString();
                } else if (s[i] >= 'a' && s[i] <= 'z') {
                    int j = 0;
                    for (j = 0; ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= '0' && s[i] <= '9')); ) {
                        word.append(s[i++]);}
                    finish = word.toString();
                } else {
                    String str = new String(s, i, s.length - i);
                    System.out.println("\"" + str + "\"unavaiable");
                    exit(0);}}
            System.out.println("终结符:" + finish);
            line = line.substring(finish.length());
            return finish;
        }
        line = line.substring(finish.length() - 1);
        return line;
    }

    private boolean isNum(String s) {
        char[] a = s.toCharArray();
        if (a[0] >= '0' && a[0] <= '9') {
            return true;
        } else {
            return false;
        }
    }

    private String lower(String line) {
        char s[] = line.toCharArray();
        for (int i = 0; i < line.length(); i++) {
            if (s[i] >= 'A' && s[i] <= 'Z') {
                s[i] += -'A' + 'a';
            }
        }

        return Arrays.toString(s);
    }

    private String trim(String line) {
        if (line.length() == 0) {
            return line;
        }
        //如果有空格，清除空格
        //

        return line;
    }
}
