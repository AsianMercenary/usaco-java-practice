package chapter_4.section_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: ditch
 */
/**

 @author William
 */
public class ditch {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("ditch.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(t.nextToken());
        int M = Integer.parseInt(t.nextToken());
        int[][] ditches = new int[M + 1][M + 1];
        for (int i = 0; i < N; i++) {
            t = new StringTokenizer(r.readLine());
            int S = Integer.parseInt(t.nextToken());
            int E = Integer.parseInt(t.nextToken());
            int C = Integer.parseInt(t.nextToken());
            ditches[S][E] += C;
        }

        int tot = 0;
        while (true) {
            int[] flow = new int[M + 1];
            boolean[] visited = new boolean[M + 1];
            int[] parent = new int[M + 1];
            flow[1] = Integer.MAX_VALUE;
            int n;
            while (true) {
                int max = 0;
                n = -1;
                for (int i = 1; i <= M; i++) {
                    if (flow[i] > max && !visited[i]) {
                        max = flow[i];
                        n = i;
                    }
                }
                if (n == -1 || n == M) {
                    break;
                }
                visited[n] = true;
                for (int j = 1; j <= M; j++) {
                    if (flow[j] < Math.min(max, ditches[n][j])) {
                        parent[j] = n;
                        flow[j] = Math.min(max, ditches[n][j]);
                    }
                }
            }
            
            if (n == -1) {
                break;
            }
            
            int c = flow[M];
            tot += c;
            
            int cn = M;
            while (cn != 1) {
                int nn = parent[cn];
                ditches[nn][cn] -= c;
                ditches[cn][nn] += c;
                cn = nn;
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("ditch.out"));
        System.out.println(tot);
        w.println(tot);
        w.close();
    }
}
