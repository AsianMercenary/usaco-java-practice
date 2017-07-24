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
TASK: rockers
 */
/**
 *
 * @author William
 */
public class rockers {

    static int N, T, M, songs[];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("rockers.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        N = Integer.parseInt(t.nextToken());
        T = Integer.parseInt(t.nextToken());
        M = Integer.parseInt(t.nextToken());

        songs = new int[N];
        t = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            songs[i] = Integer.parseInt(t.nextToken());
        }

        int out = search(0, 0, T);
        PrintWriter w = new PrintWriter(new FileWriter("rockers.out"));
        w.println(out);
        w.close();
    }

    public static int search(int current, int disk, int remainingSpace) {
        if (current == N) {
            return 0;
        }
        int choose = Integer.MIN_VALUE;
        int skip = search(current + 1, disk, remainingSpace);
        if (remainingSpace < songs[current]) {
            if (disk < M - 1) {
                if (songs[current] <= T) {
                    choose = 1 + search(current + 1, disk + 1, T - songs[current]);
                }
            }
        } else {
            choose = 1 + search(current + 1, disk, remainingSpace - songs[current]);
        }
        return Math.max(skip, choose);
    }
}
