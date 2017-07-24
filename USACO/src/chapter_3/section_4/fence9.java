package chapter_3.section_4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: fence9
 */

/**
 *
 * @author William 
 */
public class fence9 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("fence9.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(t.nextToken());
        int m = Integer.parseInt(t.nextToken());
        int p = Integer.parseInt(t.nextToken());
        
        int pts = 0;
        if (p >= n) {
            for (int i = 1; i < n; i++) {
                int yceil = (int) Math.ceil((double) m * i / n);
                pts += Math.max(0, yceil - 1);
            }
            for (int i = Math.max(1, n); i < p; i++) {
                int yceil = (int) Math.ceil((double) -m * (i - p) / (p - n));
                pts += Math.max(0, yceil - 1);
            }
        } else {
            for (int i = 1; i <= p; i++) {
                int yceil = (int) Math.ceil((double) m * i / n);
                pts += Math.max(0, yceil - 1);
            }
            for (int i = p + 1; i < n; i++) {
                int yceil = (int) Math.ceil((double) m * i / n);
                int yfloor = (int) Math.floor(((double) m * (i - p) / (n - p)));
                pts += Math.max(0, yceil - 1 - yfloor);
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("fence9.out"));
        w.println(pts);
        w.close();
    }
}
