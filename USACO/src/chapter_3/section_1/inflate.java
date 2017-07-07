package chapter_3.section_1;

/*
 ID: william164
 LANG: JAVA
 TASK: inflate
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

 @author William
 */
public class inflate {

    static int M;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("inflate.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        M = Integer.parseInt(t.nextToken());
        N = Integer.parseInt(t.nextToken());
        Category[] categories = new Category[N];
        for (int i = 0; i < N; i++) {
            t = new StringTokenizer(r.readLine());
            int p = Integer.parseInt(t.nextToken());
            int m = Integer.parseInt(t.nextToken());

            categories[i] = new Category();
            categories[i].pts = p;
            categories[i].mins = m;
        }

        Arrays.sort(categories);
        int currentMin = -1;
        int max = 0;
        Category[] newcat = new Category[N];
        int s = 0;
        for (int i = 0; i < N; i++) {
            if (categories[i].mins != currentMin) {
                //System.out.println(max * (categories[i].mins / currentMin));
                if (categories[i].pts > max * (categories[i].mins / currentMin) || currentMin == -1) {
                    newcat[s++] = categories[i];
                    currentMin = categories[i].mins;
                    max = categories[i].pts;
                }
            }
        }
        categories = newcat;
        N = s;

        //System.out.println(Arrays.toString(categories));

        int[] p = new int[M + 1];
        int[] m = new int[M + 1];
        int[] values = new int[M + 1];
        int size = 1;
        while (size > 0) {
            int[] tp = new int[M + 1];
            int[] tm = new int[M + 1];
            int[] minindex = new int[M + 1];
            for (int i = 0; i < M + 1; i++) {
                minindex[i] = -1;
            }
            int ts = 0;
            for (int i = 0; i < size; i++) {
                int newmin;
                int j = 0;
                while (j < N && (newmin = m[i] + categories[j].mins) <= M) {
                    int newpt = p[i] + categories[j].pts;
                    if (newmin <= M && newpt > values[newmin]) {
                        for (int k = newmin; k < M + 1 && values[k] < newpt; k++) {
                            values[k] = newpt;
                        }
                        if (minindex[newmin] != -1) {
                            tp[minindex[newmin]] = newpt;
                        } else {
                            tp[ts] = newpt;
                            tm[ts] = newmin;
                            minindex[newmin] = ts;
                            ts++;
                        }
                    }
                    j++;
                }

            }
            p = tp;
            m = tm;
            size = ts;
        }
        //System.out.println(values[M]);
        PrintWriter w = new PrintWriter(new FileWriter("inflate.out"));
        w.println(values[M]);
        w.close();
    }
}

class Category implements Comparable<Category> {

    int mins;
    int pts;

    @Override
    public int compareTo(Category o) {
        if (this.mins == o.mins) {
            return o.pts - this.pts;
        }
        return this.mins - o.mins;
    }

    public String toString() {
        return mins + " " + pts;
    }
}
