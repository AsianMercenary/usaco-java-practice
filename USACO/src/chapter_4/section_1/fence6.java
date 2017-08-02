package chapter_4.section_1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: fence6
 */
/**

 @author William
 */
public class fence6 {

    static int N, weights[];
    static byte connections[][];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("fence6.in"));
        N = Integer.parseInt(r.readLine());
        connections = new byte[N + 1][N + 1];
        weights = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            int s = Integer.parseInt(t.nextToken());
            int L = Integer.parseInt(t.nextToken());
            int N1 = Integer.parseInt(t.nextToken());
            int N2 = Integer.parseInt(t.nextToken());

            weights[s] = L;

            t = new StringTokenizer(r.readLine());
            for (int j = 0; j < N1; j++) {
                connections[s][Integer.parseInt(t.nextToken())] = 1;
            }

            t = new StringTokenizer(r.readLine());
            for (int j = 0; j < N2; j++) {
                connections[s][Integer.parseInt(t.nextToken())] = 2;
            }
        }

        for (int i = 1; i <= N; i++) {
            dijikstra(i, (byte) 1);
            dijikstra(i, (byte) 2);
        }

        PrintWriter w = new PrintWriter(new FileWriter("fence6.out"));
        w.println(output);
        System.out.println(output);
        w.close();
    }
    static int output = Integer.MAX_VALUE;

    public static void dijikstra(int vertex, byte val) {
        int[] dists = new int[N + 1];
        byte[] mode = new byte[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        ArrayList<Integer> q = new ArrayList<>();
        for (int j = 1; j < connections[vertex].length; j++) {
            if (connections[vertex][j] == val) {
                dists[j] = weights[j];
                mode[j] = (byte) (3 - (short) connections[j][vertex]);
                q.add(j);
            }
        }
        while (true) {
            int min;
            do {
                min = 0;
                int minindex = -1;
                for (int i = 0; i < q.size(); i++) {
                    if (dists[q.get(i)] < dists[min]) {
                        min = q.get(i);
                        minindex = i;
                    }
                }
                if (minindex == -1) {
                    return;
                }
                q.remove(minindex);
                if (min == vertex) {
                    output = Math.min(output, dists[vertex]);
                    return;
                }
            } while (visited[min]);

            visited[min] = true;
            byte test = mode[min];

            for (int v = 1; v <= N; v++) {
                if (connections[min][v] == test) {
                    if (visited[v]) {
                        output = Math.min(output, dists[min] + dists[v]);
                    }
                    if (dists[min] + weights[v] < dists[v]) {
                        dists[v] = dists[min] + weights[v];
                        mode[v] = (byte) (3 - (short) connections[v][min]);
                        q.add(v);
                    }
                }
            }
        }
    }
}
