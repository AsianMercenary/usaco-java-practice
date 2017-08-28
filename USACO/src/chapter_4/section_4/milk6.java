package chapter_4.section_4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: milk6
 */
/**

 @author William
 */
public class milk6 {

    static int begin[];
    static int end[];
    static long weights[];
    static int N, M;
    static long[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("milk6.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        N = Integer.parseInt(t.nextToken());
        M = Integer.parseInt(t.nextToken());
        edges = new long[N + 1][N + 1];
        begin = new int[M + 1];
        end = new int[M + 1];
        weights = new long[M + 1];
        for (int i = 1; i < M + 1; i++) {
            t = new StringTokenizer(r.readLine());
            begin[i] = Integer.parseInt(t.nextToken());
            end[i] = Integer.parseInt(t.nextToken());
            weights[i] = Integer.parseInt(t.nextToken()) * 1001L + 1;
            edges[begin[i]][end[i]] += weights[i];
        }
        //sort(0, M);

        int C = 0;
        long flow = maxflow();
        String output = "";
        int T = 0;
        for (int i = 1; i < M + 1; i++) {
            edges[begin[i]][end[i]] -= weights[i];
            long currflow = maxflow();

            if (currflow == flow - weights[i]) {
                output += "\n" + i;
                C += weights[i] / 1001;
                T++;
                flow = currflow;
            } else {
                edges[begin[i]][end[i]] += weights[i];
            }
        }

        PrintWriter w = new PrintWriter(new FileWriter("milk6.out"));
        w.println(C + " " + T + output);
        //System.out.println(C + " " + T + output);
        w.close();
    }

    

    public static long maxflow() {
        long max = 0;
        long[][] e = new long[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                e[i][j] = edges[i][j];
            }
        }
        while (true) {
            long[] flow = new long[N + 1];
            boolean[] visited = new boolean[N + 1];
            int[] parent = new int[N + 1];
            flow[1] = Integer.MAX_VALUE;
            int maxv;
            while (true) {
                long maxval = 0;
                maxv = -1;
                for (int i = 1; i < N + 1; i++) {
                    if (!visited[i] && flow[i] > maxval) {
                        maxv = i;
                        maxval = flow[i];
                    }
                }
                if (maxv == -1 || maxv == N) {
                    break;
                }
                visited[maxv] = true;
                for (int i = 1; i < N + 1; i++) {
                    if (Math.min(maxval, e[maxv][i]) > flow[i]) {
                        flow[i] = Math.min(maxval, e[maxv][i]);
                        parent[i] = maxv;
                    }
                }
            }

            if (maxv == -1) {
                break;
            }

            long f = flow[N];
            max += f;

            int v = N;
            while (v != 1) {
                int p = parent[v];
                e[v][p] += f;
                e[p][v] -= f;
                v = p;
            }
        }
        return max;
    }
}
