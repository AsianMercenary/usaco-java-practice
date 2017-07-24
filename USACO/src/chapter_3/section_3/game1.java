package chapter_3.section_3;

/*
 ID: william164
 LANG: JAVA
 TASK: game1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**

 @author William
 */
public class game1 {

    static int[] board;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("game1.in"));
        N = Integer.parseInt(r.readLine());
        board = new int[N];

        String line;
        int pos = 0;
        int sum = 0;
        while ((line = r.readLine()) != null) {
            StringTokenizer t = new StringTokenizer(line);
            while (t.hasMoreTokens()) {
                board[pos] = Integer.parseInt(t.nextToken());
                sum += board[pos];
                pos++;
            }
        }
        T = new int[N][N];
        visited = new boolean[N][N];
        int p1 = search(0, N - 1, true);
        PrintWriter p = new PrintWriter(new FileWriter("game1.out"));
        p.println(p1 + " " + (sum - p1));
        p.close();
    }

    static int[][] T;
    static boolean[][] visited;

    public static int search(int start, int end, boolean p1) {
        
        if (!visited[start][end]) {
            if (start == end) {
                if (p1) {
                    T[start][end] = board[start];
                } else {
                    T[start][end] = 0;
                }
            } else if (p1) {
                T[start][end] = Math.max(board[start] + search(start + 1, end, !p1), board[end] + search (start, end - 1, !p1));
            } else {
                T[start][end] = Math.min(search(start + 1, end, !p1), search(start, end - 1, !p1));
            }
            visited[start][end] = true;
        }
        return T[start][end];
    }
}
