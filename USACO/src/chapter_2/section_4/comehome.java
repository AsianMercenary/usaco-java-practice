package chapter_2.section_4;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: comehome
 */
/**

 @author William
 */
public class comehome {

    static int P;
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("comehome.in"));
        P = Integer.parseInt(r.readLine());
        edges = new int[52][52];
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                edges[i][j] = 100000;
            }
        }

        for (int i = 0; i < P; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            int a = t.nextToken().charAt(0) - 65;
            if (a > 25) {
                a = a + 65 - 97 + 26;
            }

            int b = t.nextToken().charAt(0) - 65;
            if (b > 25) {
                b = b + 65 - 97 + 26;
            }

            edges[a][b] = edges[b][a] = Math.min(Integer.parseInt(t.nextToken()), edges[a][b]);
        }

        /*for (int[] a : edges) {
            System.out.println(Arrays.toString(a));
        }*/

        boolean[] visited = new boolean[52];
        int[] dist = new int[52];
        for (int i = 0; i < 52; i++) {
            dist[i] = 100000;
        }

        dist[25] = 0;

        String output = "";
        for (int i = 0; i < 52; i++) {
            int min = Integer.MAX_VALUE;
            int minV = -1;
            for (int v = 0; v < 52; v++) {
                if (!visited[v] && dist[v] < min) {
                    minV = v;
                    min = dist[v];
                }
            }

            visited[minV] = true;

            if (minV >= 0 && minV < 25) {
                output = (char) (minV + 65) + " " + dist[minV];
                //System.out.println(Arrays.toString(dist));
                break;
            }

            for (int n = 0; n < 52; n++) {
                if (dist[minV] + edges[minV][n] < dist[n]) {
                    dist[n] = dist[minV] + edges[minV][n];
                }
            }
        }

        PrintWriter p = new PrintWriter(new FileWriter("comehome.out"));
        p.println(output);
        System.out.println(output);
        p.close();
    }
}
