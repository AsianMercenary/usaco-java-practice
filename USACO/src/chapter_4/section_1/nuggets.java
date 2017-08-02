package chapter_4.section_1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 ID: william164
 LANG: JAVA
 TASK: nuggets
 */
/**

 @author William
 */
public class nuggets {

    static int N, nuggets[];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("nuggets.in"));
        N = Integer.parseInt(r.readLine());
        nuggets = new int[N];
        int max = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            nuggets[i] = Integer.parseInt(r.readLine());
            q.add(nuggets[i]);
        }

        boolean possible = false;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (gcd(nuggets[i], nuggets[j]) == 1) {
                    possible = true;
                    break;
                }
            }
        }

        if (possible) {
            boolean more = true;
            int prev = 0;
            int round = 1;
            while (round * 2 < q.peek()) {
                round *= 2;
            }
            boolean found = false;
            while (more) {
                int v = q.poll();
                if (v <= prev) {
                    continue;
                }
                if (prev + 1 != v) {
                    max = v - 1;
                    found = false;
                }
                
                if (v >= round * 2) {
                    if (found) {
                        break;
                    }
                    found = true;
                    round *= 2;
                }
                
                prev = v;
                for (int n : nuggets) {
                    q.add(v + n);
                }
            }
        }

        PrintWriter w = new PrintWriter(new FileWriter("nuggets.out"));
        w.println(max);
        w.close();
    }

    public static int gcd(int a, int b) {
        int end = (int) Math.floor(Math.sqrt(Math.min(a, b)));
        for (int i = end; i >= 2; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
