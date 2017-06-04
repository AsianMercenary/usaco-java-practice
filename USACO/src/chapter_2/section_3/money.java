package chapter_2.section_3;

/*
 ID: william164
 LANG: JAVA
 TASK: money
 */

import java.io.*;
import java.util.*;

/**

 @author William
 */
public class money {

    static long dict[];
    static int V, N, system[];

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("money.in"));
        StringTokenizer s = new StringTokenizer(b.readLine());
        V = Integer.parseInt(s.nextToken());
        N = Integer.parseInt(s.nextToken());

        dict = new long[N + 1];
        system = new int[V];

        int index = 0;
        while (index < V) {
            s = new StringTokenizer(b.readLine());
            while (s.hasMoreTokens()) {
                system[index++] = Integer.parseInt(s.nextToken());
            }
        }
        for (int coin : system) {
            long newdict[] = new long[N + 1];
            for (int j = 0; j < dict.length; j++) {
                newdict[j] += dict[j];
            }
            for (int j = coin; j <= N; j += coin) {
                newdict[j]++;
                for (int k = 1; k < dict.length; k++) {
                    int sum = j + k;
                    if (sum > N) {
                        break;
                    }
                    newdict[sum] += dict[k];
                }
            }

            dict = newdict;
        }

        PrintWriter p = new PrintWriter(new FileWriter("money.out"));
        p.println(dict[N]);
        p.close();
    }
}
