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
 TASK: stall4
 */
/**

 @author William
 */
public class stall4 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("stall4.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(t.nextToken());
        int M = Integer.parseInt(t.nextToken());
        int[][] capacities = new int[N + M + 2][N + M + 2];
        for (int i = 1; i <= N; i++) {
            t = new StringTokenizer(r.readLine());
            int S = Integer.parseInt(t.nextToken());
            for (int j = 1; j <= S; j++) {
                capacities[i][N + Integer.parseInt(t.nextToken())] = 1;
            }
            
            capacities[0][i] = 1;
        }
        
        for (int j = 1; j <= M; j++) {
            capacities[N + j][N + M + 1] = 1;
        }
        
        int total = 0;
        while (true) {
            boolean[] visited = new boolean[N + M + 2];
            int[] parent = new int[N + M + 2];
            int[] flow = new int[N + M + 2];
            flow[0] = Integer.MAX_VALUE;
            int maxv;
            while (true) {
                maxv = -1;
                int max = 0;
                for (int i = 0; i < N + M + 2; i++) {
                    if (flow[i] > max && !visited[i]) {
                        max = flow[i];
                        maxv = i;
                    }
                }
                if (maxv == N + M + 1) {
                    break;
                }
                if (maxv == -1) {
                    break;
                }
                //System.out.println(maxv);
                visited[maxv] = true;
                for (int n = 0; n < N + M + 2; n++) {
                    if (flow[n] < Math.min(max, capacities[maxv][n])) {
                        flow[n] = Math.min(max, capacities[maxv][n]);
                        parent[n] = maxv;
                    }
                }
            }
            if (maxv == -1) {
                break;
            }
            int f = flow[N + M + 1];
            total += f;
            
            int cn = N + M + 1;
            while (cn != 0) {
                int nn = parent[cn];
                capacities[nn][cn] -= f;
                capacities[cn][nn] += f;
                cn = nn;
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("stall4.out"));
        w.println(total);
        w.close();
    }
}
