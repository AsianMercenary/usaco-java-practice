package chapter_3.section_3;


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
TASK: fence
 */
/**
 *
 * @author William
 */
public class fence {

    static int fences;
    static int[][] edges;
    static int[] nodes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("fence.in"));
        fences = Integer.parseInt(r.readLine());
        edges = new int[501][501];
        nodes = new int[501];
        for (int i = 0; i < fences; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(t.nextToken());
            int b = Integer.parseInt(t.nextToken());
            nodes[a]++;
            nodes[b]++;
            edges[a][b]++;
            edges[b][a]++;
        }
        int index = Integer.MAX_VALUE;
        boolean hasOdd = false;
        for (int i = 1; i < 501; i++) {
            if (nodes[i] > 0) {
                if (hasOdd) {
                    if (nodes[i] % 2 == 1 && i < index) {
                        index = i;
                    }
                } else {
                    if (nodes[i] % 2 == 1) {
                        index = i;
                        hasOdd = true;
                    } else if (i < index) {
                        index = i;
                    }
                }
            }
        }
        
        path = new int[fences + 1];
        search(index);

        /*for (int[] c : edges) {
            System.out.println(Arrays.toString(c));
        }*/
        PrintWriter p = new PrintWriter(new FileWriter("fence.out"));
        for (int i = fences; i >= 0; i--) {
            p.println(path[i]);
        }
        p.close();
    }
    
    static int[] path;
    static int pos = 0;
    public static void search(int index) {
        if (nodes[index] == 0) {
            path[pos] = index;
            pos++;
        } else {
            while (nodes[index] > 0) {
                for (int i = 1; i < 501; i++) {
                    if (edges[index][i] > 0) {
                        nodes[index]--;
                        nodes[i]--;
                        edges[index][i]--;
                        edges[i][index]--;
                        search(i);
                    }
                }
            }
            path[pos] = index;
            pos++;
        }
    }
}
