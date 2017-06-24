package chapter_2.section_4;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: cowtour
 */
/**

 @author William
 */
public class cowtour {

    static int N;
    static int[] x;
    static int[] y;
    static double[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("cowtour.in"));
        N = Integer.parseInt(r.readLine());
        dist = new double[N + 1][N + 1];
        x = new int[N + 1];
        y = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            x[i] = Integer.parseInt(t.nextToken());
            y[i] = Integer.parseInt(t.nextToken());
        }

        for (int i = 0; i < N; i++) {
            char[] line = r.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (line[j] == '1') {
                    dist[i + 1][j + 1] = Math.sqrt(Math.pow(x[i + 1] - x[j + 1], 2) + Math.pow(y[i + 1] - y[j + 1], 2));
                } else if (i != j) {
                    dist[i + 1][j + 1] = -1;
                }
            }
        }

        for (int k = 1; k <= N; k++) {
            paths(dist, k);
        }

        /*for (float[] a : dist) {
            System.out.println(Arrays.toString(a));
        }*/

        double[] max = new double[N + 1];
        for (int i = 1; i <= N; i++) {
            double m = Double.MIN_VALUE;
            for (int j = 1; j <= N; j++) {
                m = Math.max(m, dist[i][j]);
            }
            max[i] = m;
        }

        double[] maxgraph = new double[N + 1];
        boolean[] tested = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!tested[i]) {
                double m = Double.MIN_VALUE;
                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] != -1) {
                        m = Math.max(max[j], m);
                        tested[j] = true;
                    }
                }

                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] != -1) {
                        maxgraph[j] = m;
                    }
                }
            }
        }

        double min = Double.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                if (dist[i][j] == -1) {
                    min = Math.min(min,
                            Math.max(Math.max(max[i] + max[j] + Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)), maxgraph[i]), maxgraph[j]));
                }
            }
        }

        PrintWriter p = new PrintWriter(new FileWriter("cowtour.out"));
        DecimalFormat d = new DecimalFormat(".000000");
        p.println(d.format(min));
        System.out.println(d.format(min));
        p.close();
    }
    
    public static void paths(double[][] dist, int k) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][k] != -1 && dist[k][j] != -1 && (dist[i][j] > dist[i][k] + dist[k][j] || dist[i][j] == -1)) {
                      dist[i][j] = dist[i][k] + dist[k][j];
                } 
            }
        }
    } 
}
