package chapter_1.section_2;

/*
ID: william164
LANG: JAVA
TASK: milk2
 */
import java.io.*;
import java.util.*;

/**
 *
 * @author William
 */
public class milk2 {

    public static void main(String args[]) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("milk2.in"));
        int N = Integer.parseInt(b.readLine());

        boolean[] time = new boolean[1000001];

        int start = Integer.MAX_VALUE;
        int stop = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer s = new StringTokenizer(b.readLine());
            int low = Integer.parseInt(s.nextToken()) + 1;
            int high = Integer.parseInt(s.nextToken());

            start = Math.min(start, low);
            stop = Math.max(stop, high);

            for (int j = low; j <= high; j++) {
                time[j] = true;
            }
        }

        int i = start;
        boolean state = true;
        int maxMilk = 0, maxIdle = 0;

        int current = 0;
        while (i <= stop) {
            if (time[i] != state) {
                if (state) {
                    maxMilk = Math.max(current, maxMilk);
                } else {
                    maxIdle = Math.max(current, maxIdle);
                }
                state = !state;
                current = 0;
            }
            current++;
            i++;
        }
        if (state) {
            maxMilk = Math.max(current, maxMilk);
        } else {
            maxIdle = Math.max(current, maxIdle);
        }

        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        p.println(maxMilk + " " + maxIdle);
        p.close();
    }
}
