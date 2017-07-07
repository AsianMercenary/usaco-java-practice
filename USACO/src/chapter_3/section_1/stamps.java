package chapter_3.section_1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: stamps
 */
/**
 *
 * @author William
 */
public class stamps {

    static int K, N, cents[];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("stamps.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        K = Integer.parseInt(t.nextToken());
        N = Integer.parseInt(t.nextToken());
        cents = new int[N];
        String line;
        int index = 0;
        while ((line = r.readLine()) != null) {
            t = new StringTokenizer(line);
            while (t.hasMoreTokens()) {
                cents[index++] = Integer.parseInt(t.nextToken());
            }
        }

        queue.add(0);
        for (int i = 0; running && i < K; i++) {
            search(i);
        }

        PrintWriter p = new PrintWriter(new FileWriter("stamps.out"));
        p.println(o);
        p.close();
    }
    static int[] vals = new int[2000001];
    static int val = 0;
    static int o = 0;
    static ArrayList<Integer> queue = new ArrayList<>();
    static boolean running = true;
    
    public static void search(int i) {
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> newqueue = new ArrayList<>();
        for (int j : queue) {
            val = j;
            for (int c : cents) {
                val += c;
                if (vals[val] > i + 1 || vals[val] == 0) {
                    vals[val] = i + 1;
                    newqueue.add(val);
                }

                min = Math.min(min, val);
                val -= c;
            }
        }

        for (int j = o + 1; j < vals.length && vals[j] != 0; j++) {
            o = j;
        }
        
        //System.out.println(newqueue);

        if (min > o) {
            running = false;
        } else {
            queue = newqueue;
        }
    }
}
