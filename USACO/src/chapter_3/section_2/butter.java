package chapter_3.section_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: butter
 */
/**
 *
 * @author William
 */
public class butter {

    static int N, P, C;
    static int[][] dist;
    static int[] location;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("butter.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int total = 0;
        N = Integer.parseInt(t.nextToken());
        P = Integer.parseInt(t.nextToken());
        C = Integer.parseInt(t.nextToken());
        location = new int[P];
        for (int i = 0; i < N; i++) {
            int pasture = Integer.parseInt(r.readLine()) - 1;
            if (location[pasture] == 0) {
                total++;
            }
            location[pasture]++;
        }
        dist = new int[P][P];
        int[][] edges = new int[P][P];
        boolean[][] adj = new boolean[P][P];

        for (int i = 0; i < P; i++) {
            for (int j = 0; j < P; j++) {
                dist[i][j] = 99999999;
                edges[i][j] = 99999999;
            }
            edges[i][i] = dist[i][i] = 0;
        }

        for (int i = 0; i < C; i++) {
            t = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(t.nextToken()) - 1;
            int b = Integer.parseInt(t.nextToken()) - 1;
            int l = Integer.parseInt(t.nextToken());
            edges[a][b] = edges[b][a] = l;
            adj[a][b] = adj[b][a] = true;
        }

        boolean[][] visited = new boolean[P][P];
        int[] heap = new int[1000];
        int size;
        
        for (int v = 0; v < P; v++) {
            heap[0] = v;
            size = 1;
            
            if (location[v] == 0) {
                continue;
            }
            for (int c = 0; c < P; c++) {

                //find min
                int minindex = -1;
                while (true) {
                    
                    minindex = heap[0];
                    heap[0] = heap[--size];
                    heap[size] = 0;

                    int i = 0;
                    while (true) {
                        int a = 2 * i + 1, b = a + 1;
                        if (b < size) {
                            if (dist[v][heap[a]] < dist[v][heap[b]]) {
                                if (dist[v][heap[a]] < dist[v][heap[i]]) {
                                    int temp = heap[a];
                                    heap[a] = heap[i];
                                    heap[i] = temp;
                                    i = a;
                                } else {
                                    break;
                                }
                            } else if (dist[v][heap[b]] < dist[v][heap[i]]) {
                                if (dist[v][heap[b]] < dist[v][heap[i]]) {
                                    int temp = heap[b];
                                    heap[b] = heap[i];
                                    heap[i] = temp;
                                    i = b;
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else if (a < size && dist[v][heap[a]] < dist[v][heap[i]]) {
                            int temp = heap[a];
                            heap[a] = heap[i];
                            heap[i] = temp;
                            i = a;
                        } else {
                            break;
                        }
                    }

                    if (!visited[v][minindex]) {
                        break;
                    }
                }

                visited[v][minindex] = true;

                for (int i = 0; i < P; i++) {
                    if (adj[minindex][i] && dist[v][i] > dist[v][minindex] + edges[minindex][i]) {
                        dist[v][i] = dist[v][minindex] + edges[minindex][i];
                        int n = size;
                        heap[size++] = i;

                        while (true) {
                            int p = (n + 1) / 2 - 1;
                            if (p < 0) {
                                break;
                            }
                            if (dist[v][heap[n]] < dist[v][heap[p]]) {
                                int temp = heap[n];
                                heap[n] = heap[p];
                                heap[p] = temp;
                                n = p;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < P; i++) {
            for (int j = i; j < P; j++) {
                dist[i][j] = dist[j][i] = Math.min(dist[i][j], dist[j][i]);
            }
        }
        
        int min = 99999999;
        loop2:
        for (int i = 0; i < P; i++) {
            int sum = 0;
            for (int j = 0; j < P; j++) {
                if (dist[i][j] == 99999999 && location[j] != 0) {
                    break loop2;
                }
                sum += dist[i][j] * location[j];
            }
            min = Math.min(min, sum);
        }
        PrintWriter p = new PrintWriter(new FileWriter("butter.out"));
        p.println(min);
        p.close();
    }
}
