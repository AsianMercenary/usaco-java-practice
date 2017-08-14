package chapter_4.section_3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: race3
 */

/**

 @author William
 */
public class race3 {
    static boolean[][] course;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("race3.in"));
        course = new boolean[51][51];
        String line;
        size = 0;
        while (!(line = r.readLine()).equals("-1")) {
            StringTokenizer t = new StringTokenizer(line);
            int point;
            while ((point = Integer.parseInt(t.nextToken())) != -2) {
                course[size][point] = true;
            }
            size++;
        }
        String u = "";
        int uc = 0;
        String s = "";
        int sc = 0;
        for (int i = 1; i < size - 1; i++) {
            boolean[] pointsA = new boolean[size], pointsB = new boolean[size];
            unavoidable(0, i, pointsA, true);
            if (pointsA[size - 1]) {
                continue;
            }
            u += " " + i;
            uc++;
            unavoidable(i, -5, pointsB, false);
            boolean split = true;
            for (int j = 0; j < size; j++) {
                if (pointsA[j] && pointsB[j]) {
                    split = false;
                    break;
                }
            }
            if (split) {
                //System.out.println(Arrays.toString(pointsA) + "\n" + Arrays.toString(pointsB));
                s += " " + i;
                sc++;
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("race3.out"));
        w.println(uc + u);
        w.println(sc + s);
        w.close();
    }
    
    public static void unavoidable(int point, int test, boolean[] points, boolean premature) {
        if (premature && points[size - 1]) {
            return;
        }
        points[point] = true;
        for (int i = 0; i < size; i++) {
            if (!points[i] && course[point][i] && i != test) {
                unavoidable(i, test, points, premature);
            }
        }
    }
}
