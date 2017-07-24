package chapter_3.section_3;


import java.util.*;
import java.io.*;

/*
ID: william164
LANG: JAVA
TASK: shopping
 */

/**
 *
 * @author William
 */
public class shopping {

    static int s, b;
    static int[][] offer;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("shopping.in"));
        s = Integer.parseInt(r.readLine());
        int[][] items = new int[s][5];
        int[][] amount = new int[s][5];
        int[] price = new int[s];

        for (int i = 0; i < s; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            int n = Integer.parseInt(t.nextToken());
            for (int j = 0; j < n; j++) {
                int c = Integer.parseInt(t.nextToken());
                int k = Integer.parseInt(t.nextToken());
                items[i][j] = c;
                amount[i][j] = k;
            }
            int p = Integer.parseInt(t.nextToken());
            price[i] = p;
        }

        int[] codeToItem = new int[1000];
        Arrays.fill(codeToItem, -1);
        b = Integer.parseInt(r.readLine());
        cost = new int[s + b];
        offer = new int[s + b][5];
        int[] finCount = new int[5];
        for (int i = 0; i < b; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            int c = Integer.parseInt(t.nextToken());
            int k = Integer.parseInt(t.nextToken());
            int p = Integer.parseInt(t.nextToken());
            codeToItem[c] = i;
            cost[i] = p;
            offer[i][i] = 1;
            finCount[i] += k;
        }
        

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < 5; j++) {
                int code = codeToItem[items[i][j]];
                if (code == -1) {
                    continue;
                }
                offer[i + b][code] = amount[i][j];
            }
            cost[i + b] = price[i];
        }
        /*
        int o = 0;
        for (int[] i : offer) {
            System.out.println(Arrays.toString(i) + " " + cost[o]);
            o++;
        }*/
        
        
        T = new int[s + b][6][6][6][6][6];
        visited = new boolean[s + b][6][6][6][6][6];

        int mincost = search(0, finCount[0], finCount[1], finCount[2], finCount[3], finCount[4]);
        
        PrintWriter p = new PrintWriter(new FileWriter("shopping.out"));
        p.println((mincost != Integer.MAX_VALUE) ? mincost : 0);
        p.close();
    }
    
    static int[][][][][][] T;
    static boolean[][][][][][] visited;
    
    public static int search(int index, int i1, int i2, int i3, int i4, int i5) {
        
        if (i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i5 < 0) {
            return Integer.MAX_VALUE;
        }

        if (index >= s + b) {
            if (i1 == 0 && i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }
        
        if (visited[index][i1][i2][i3][i4][i5]) {
            return T[index][i1][i2][i3][i4][i5];
        }
       
        int[] currentOffer = offer[index];
        int s0 = search(index + 1, i1, i2, i3, i4, i5);
        if (s0 < 0) {
            s0 = Integer.MAX_VALUE;
        }
        int s1 = cost[index] + search(index + 1, i1 - currentOffer[0], i2 - currentOffer[1], i3 - currentOffer[2], i4 - currentOffer[3], i5 - currentOffer[4]);
        if (s1 < 0) {
            s1 = Integer.MAX_VALUE;
        }
        int s2 = 2 * cost[index] + search(index + 1, i1 - currentOffer[0] * 2, i2 - currentOffer[1] * 2, i3 - currentOffer[2] * 2, i4 - currentOffer[3] * 2, i5 - currentOffer[4] * 2);
        if (s2 < 0) {
            s2 = Integer.MAX_VALUE;
        }
        int s3 = 3 * cost[index] + search(index + 1, i1 - currentOffer[0] * 3, i2 - currentOffer[1] * 3, i3 - currentOffer[2] * 3, i4 - currentOffer[3] * 3, i5 - currentOffer[4] * 3);
        if (s3 < 0) {
            s3 = Integer.MAX_VALUE;
        }
        int s4 = 4 * cost[index] + search(index + 1, i1 - currentOffer[0] * 4, i2 - currentOffer[1] * 4, i3 - currentOffer[2] * 4, i4 - currentOffer[3] * 4, i5 - currentOffer[4] * 4);
        if (s4 < 0) {
            s4 = Integer.MAX_VALUE;
        }
        int s5 = 5 * cost[index] + search(index + 1, i1 - currentOffer[0] * 5, i2 - currentOffer[1] * 5, i3 - currentOffer[2] * 5, i4 - currentOffer[3] * 5, i5 - currentOffer[4] * 5);
        if (s5 < 0) {
            s5 = Integer.MAX_VALUE;
        }
        visited[index][i1][i2][i3][i4][i5] = true;
        T[index][i1][i2][i3][i4][i5] = Math.min(Math.min(Math.min(s0, s1), s2), Math.min(s3, Math.min(s4, s5)));
        //System.out.println(index + " " + i1 + " " + i2 + " " + i3 + " "  + i4 + " " + i5 + " " + T[index][i1][i2][i3][i4][i5]);
        return T[index][i1][i2][i3][i4][i5];
    }
}
