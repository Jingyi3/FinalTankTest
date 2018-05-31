package lab4;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by ZJYYY on 2018/5/30.
 */
public class Test4 {
    private static String line;//
    private static String lookahead;//当前扫描的输入符号
    private static String s1[] = {"=", "<", "<=", ">", ">=", ":=", ",", ";", "."};
    private static char p;
    private static Scanner reader = new Scanner(System.in);
    private static String input;
    private static StringBuffer sb = new StringBuffer("");
    private static StringBuffer sb0 = new StringBuffer("0");

    public static void main(String[] args) {

        while (true) {
            System.out.println("请输入表达式:");
            sb.append(reader.next());
            sb.append("#");
            input = sb.toString();

            char line[] = input.toCharArray();
            if (line[0] == '+' || line[0] == '-') {
                sb0.append(input);
            }

            char line2[] = sb0.toString().toCharArray();
            //lookahead = getToken();


        }
    }

    public void matchToken(String a) {
        if (!lookahead.equals(a)) {
            System.out.println("Error!");
            exit(0);
        } else {
            lookahead = getToken();
        }

    }

    public void ParseS() {
        System.out.println("S1");
        float Ev = ParseE();
        System.out.println("result is:" + Ev);
    }


    public float ParseE() {
        float Ev = 0;
        if (isNum(lookahead) || lookahead.equals("(")) {
            float Tv = ParseT();
            float Ri = Tv;
            float Rs = ParseR(Ri);
            Ev = Rs;
        } else {

            exit(0);
        }

        return Ev;
    }

    public float ParseR(float i) {
        float Rs = 0;
        if (lookahead.equals("+")) {
            matchToken(lookahead);
            float Tv = ParseT();
            float R1i = i + Tv;
            float R1s = ParseR(R1i);
            Rs = R1s;
            System.out.println("Rs = " + Rs);
        } else if (lookahead.equals("-")) {
            matchToken(lookahead);
            float Tv = ParseT();
            float R1i = i - Tv;
            float R1s = ParseR(R1i);
            Rs = R1s;
            System.out.println("Rs = " + Rs);

        } else if (lookahead.equals(")") || lookahead.equals("#")) {
            Rs = i;
        } else {
            System.out.println("Error3! ");
            exit(0);
        }
        return Rs;
    }

    public float ParseT() {
        float Tv = 0;
        if (isNum(lookahead) || lookahead.equals("(")) {
            float Fv = ParseF();
            float Pi = Fv;
            float Ps = ParseP(Pi);
            Tv = Ps;
            System.out.println("Tv=" + Tv);
        } else {
            System.out.println("Error4!");
            exit(0);
        }
        return Tv;
    }

    public float ParseP(float i) {
        System.out.println("P");
        float Ps = 0;
        if (lookahead.equals("*")) {
            matchToken(lookahead);
            float Fv = ParseF();
            float P1i = i * Fv;
            float P1s = ParseP(P1i);
            Ps = P1s;
            System.out.println("Ps=" + Ps);
        } else if (lookahead.equals("/")) {
            matchToken(lookahead);
            float Fv = ParseF();
            if (Fv == 0) {
                System.out.println("chushu is 0");
            }

            float P1i = i / Fv;
            float P1s = ParseP(P1i);
            Ps = P1s;
            System.out.println("Ps=" + Ps);
        } else if (lookahead.equals(")") || lookahead.equals("#") ||
                lookahead.equals("+") || lookahead.equals("-")) {
            Ps = i;
        } else {
            System.out.println("Error4!");
            exit(0);
        }
        return Ps;
    }

    public float ParseF() {
        float Fv=0;
        if (isNum(lookahead)) {
            //convertFromString(Fv, lookahead);
            Fv = Float.parseFloat(lookahead);
            lookahead = getToken();
        } else if (lookahead.equals("(")) {
            matchToken("(");
            float Ev = ParseE();
            Fv = Ev;
            if (lookahead.equals(")"))
                matchToken(")");
        } else {
            System.out.println("Error5!");
            exit(0);
        }
        System.out.println("Fv=" + Fv);
        return Fv;
    }


    public String getToken() {
        char word[] = new char [20];
        String finish;
        String s = reader.next();
        while(!s.equals('\0')){
            if (s.equals(' ')){
            }
        }
        return null;
    }

    public char[] trim(char c[]) {
        return c;
    }

    public char[] lower(char c[]) {
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 'A' && c[i] <= 'Z') {
                c[i] += -'A' + 'a';
            }
        }
        return c;
    }

    public void convertFromString() {

    }

    public boolean isNum(String s) {
        return false;
    }
}


/*
* string getToken();         //获取下一个输入符
//将字符串转化为数字
template <class T>
void convertFromString(T &, const std::string &);
bool Isnum(string &);      //判断输入符是否为数字
void MatchToken(string);   //匹配终结符

string line;               //输入字符串
string lookahead;          //当前扫描的输入符号
char *p = NULL;            //指向当前输入符读取位置
* */