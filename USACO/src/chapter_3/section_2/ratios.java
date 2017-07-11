package chapter_3.section_2;

/*
 ID: william164
 LANG: JAVA
 TASK: ratios
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import static java.lang.Integer.*;
import java.util.Arrays;

/**

 @author William
 */
public class ratios {

    public static void main(String[] args) throws IOException {
        int a, b, c, d;
        double[][] m = new double[3][4];
        BufferedReader r = new BufferedReader(new FileReader("ratios.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        m[0][3] = parseInt(t.nextToken());
        m[1][3] = parseInt(t.nextToken());
        m[2][3] = parseInt(t.nextToken());
        t = new StringTokenizer(r.readLine());
        m[0][0] = parseInt(t.nextToken());
        m[1][0] = parseInt(t.nextToken());
        m[2][0] = parseInt(t.nextToken());
        t = new StringTokenizer(r.readLine());
        m[0][1] = parseInt(t.nextToken());
        m[1][1] = parseInt(t.nextToken());
        m[2][1] = parseInt(t.nextToken());
        t = new StringTokenizer(r.readLine());
        m[0][2] = parseInt(t.nextToken());
        m[1][2] = parseInt(t.nextToken());
        m[2][2] = parseInt(t.nextToken());
        for (int i = 0; i < 2; i++) {
            for (int j = i; j < 2 && m[i][i] == 0; j++) {
                for (int k = i; k < 2; k++) {
                    double[] temp = m[k];
                    m[k] = m[k + 1];
                    m[k + 1] = temp;
                }
            }
            if (m[i][i] == 0) {
                continue;
            }

            for (int j = i + 1; j < 3; j++) {
                double factor = m[j][i] / m[i][i];
                for (int k = i; k < 4; k++) {
                    m[j][k] -= m[i][k] * factor;
                }
            }
        }

        for (int i = 2; i >= 0; i--) {
            if (m[i][i] == 0) {
                continue;
            }
            m[i][3] /= m[i][i];
            m[i][i] = 1;

            for (int j = i - 1; j >= 0; j--) {
                double sub = m[j][i] * m[i][3];
                m[j][i] = 0;
                m[j][3] -= sub;
            }
        }
        
       /* for (double[] ar : m) {
            System.out.println(Arrays.toString(ar));
        }*/

        int i = 1;
        while (i <= 100) {

            for (int j = 0; j < 3; j++) {
                m[j][3] *= i;
            }

            boolean f = true;
            for (int j = 0; j < 3; j++) {
                if (Math.abs(m[j][3] - Math.round(m[j][3])) < .0000001) {
                    f |= true;
                } else {
                    f = false;
                }
            }

            if (f) {
                break;
            }

            for (int j = 0; j < 3; j++) {
                m[j][3] /= i;
            }
            i++;
        }

        PrintWriter p = new PrintWriter(new FileWriter("ratios.out"));
        a = (int) Math.round(m[0][3]);
        b = (int) Math.round(m[1][3]);
        c = (int) Math.round(m[2][3]);
        if (a < 100 && a >= 0 && b < 100 && b >= 0 && c < 100 && c >= 0) {
            p.println((int) Math.round(m[0][3]) + " " + (int) Math.round(m[1][3]) + " " + (int) Math.round(m[2][3]) + " " + i);
        } else {
            p.println("NONE");
        }
        p.close();
    }
}
