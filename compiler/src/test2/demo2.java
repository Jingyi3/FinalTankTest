package test2;

import com.sun.deploy.util.StringUtils;
import test1.Hw01;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2018/4/12.
 */
public class demo2 {


    ArrayList<String> words = new ArrayList<>();
    ArrayList<String> out = new ArrayList<>();
    private FileReader fr = null;
    private BufferedReader br = null;



    String output_filename = "./src/test2/Output/output2.txt";
    String input_filename = "./src/test2/Input/input2.txt";
    private File f = null;
    private OutputStreamWriter osw = null;
    private BufferedWriter bw1 = null;
    private FileWriter fileWriter = null;
    private PrintWriter pw = null;


    public demo2() {

        splitword(words);

        int n = 0;
        int i, j, k;
        int p = 0;

        for (i = 1; i < words.size(); i++) {
            p = 0;
            String str = words.get(i);
            char[] chars = str.toCharArray();
            if (chars[0] > '0' && chars[0] < '9') {
                for (k = 0; k < chars.length; k++) {
                    if (!Character.isDigit(chars[k])) {
                        p = -1;
                    }
                }
                if (p != -1) {
                    out.add("number");
                }
            } else {
                for (j = 0; j < Vocabulary.basic.size(); j++) {
                    if (str.equals(Vocabulary.basic.get(j))) {
                        p = 1;
                        out.add(Vocabulary.basic_code.get(j));
                    }
                }
                if (p == 0) {
                    out.add("ident");
                }


            }


            System.out.println(out);


            try {

                File f = new File(output_filename);
                if (!f.exists()) {
                    f.createNewFile();
                }
                //打开文件
                FileOutputStream fos = new FileOutputStream(output_filename);
                //设置编码集
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");


                //写出数据
                pw = new PrintWriter(osw, true);
                pw.print("");

                for (int q = 1; q < words.size(); q++) {
                    String str_out = out.get(q-1).toString();
                    String str_word = words.get(q).toString();
                    pw.append("(" + str_out + ",   " + str_word + ")" + "\r\n");
                    pw.flush();
                    System.out.println(str_out + "  " + str_word);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pw != null) pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private void splitword(ArrayList<String> words) {
        try {
            fr = new FileReader(input_filename);
            br = new BufferedReader(fr);
            String str = "";
            String temp = "";
            while ((str = br.readLine()) != null) {
                int i = 0;
                int flag = 0;
                char[]s=str.toCharArray();
                while (i<s.length && s[i] != 0 ) {
                    if ((s[i] >= 'A' && s[i] <= 'Z') | (s[i] >= 'a' && s[i] <= 'z')) {
                        if(s[i]>='A' && s[i]<='Z') s[i] += 32;
                        if (flag != 1 && flag != 2) {
                            words.add(temp);
                            temp = "";
                        }
                        temp += s[i];
                        flag = 1;
                    } else if (s[i] >= '0' && s[i] <= '9') {
                        if (flag != 1 && flag != 2) {
                            words.add(temp);
                            temp = "";
                        }
                        temp += s[i];
                        flag = 2;
                    } else if (s[i] == ' ' || s[i] == '\t') {
                        flag = 0;
                    } else {
                        if (flag != 3 || s[i] != '=') {
                            words.add(temp);
                            temp = "";
                        }
                        temp += s[i];
                        flag = 3;
                    }
                    i++;
                }
                System.out.println(words);
            }
            words.add(temp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null) br.close();
                if(fr!=null) fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        demo2 demo2 = new demo2();
    }
}
