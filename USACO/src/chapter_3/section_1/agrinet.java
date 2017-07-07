package chapter_3.section_1;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: agrinet
 */
/**

 @author William
 */
public class agrinet {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("agrinet.in"));
        N = Integer.parseInt(r.readLine().trim());
        int[][] edges = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            for (int j = 0; j < N; j++) {
                if (!t.hasMoreTokens()) {
                    t = new StringTokenizer(r.readLine());
                }
                edges[i][j] = Integer.parseInt(t.nextToken());
            }
        }
        boolean[] visited = new boolean[N];
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = 100001;
        }

        visited[0] = true;
        for (int i = 1; i < N; i++) {
            dist[i] = edges[0][i];
        }

        int cost = 0;

        for (int i = 1; i < N; i++) {
            int mindist = Integer.MAX_VALUE;
            int v = -1;
            for (int j = 0; j < N; j++) {
                if (mindist > dist[j] && !visited[j]) {
                    mindist = dist[j];
                    v = j;
                }
            }
            cost += mindist;
            visited[v] = true;

            for (int j = 0; j < N; j++) {
                if (!visited[j]) {
                    dist[j] = Math.min(edges[v][j], dist[j]);
                }
            }
        }

        System.out.println(cost);
        PrintWriter p = new PrintWriter(new FileWriter("agrinet.out"));
        p.println(cost);
        p.close();
    }
}
