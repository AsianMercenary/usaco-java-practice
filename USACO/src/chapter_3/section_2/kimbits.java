package chapter_3.section_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: kimbits
 */
/**
 *
 * @author ken
 */
public class kimbits {

    static int N, L;
    static long I;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("kimbits.in"));
        StringTokenizer s = new StringTokenizer(r.readLine());
        N = Integer.parseInt(s.nextToken());
        L = Integer.parseInt(s.nextToken());
        I = Long.parseLong(s.nextToken());

        I--;
        String output = "";
        for (int i = N - 1; i >= 0; i--) {
            long perms = 1;
            for (int j = 1; j <= i && j <= L; j++) {
                //System.out.println(i + " " + j + " " + calcPerm(i, j));
                perms += calcPerm(i, j);
            }
            if (I >= perms) {
                I -= perms;
                L--;
                output += "1";
            } else {
                output += "0";
            }
        }
        //System.out.println(I);

        PrintWriter p = new PrintWriter(new FileWriter("kimbits.out"));
        p.println(output);
        p.close();
    }

    public static long calcPerm(int n, int r) {
        int[] products = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            products[i]++;
        }
        for (int i = 1; i <= r; i++) {
            products[i]--;
        }
        for (int i = 1; i <= n - r; i++) {
            products[i]--;
        }

        double out = 1;
        for (int i = 1; i <= n; i++) {
            out *= Math.pow(i, products[i]);
        }
        return (long) Math.round(out);
    }
}
