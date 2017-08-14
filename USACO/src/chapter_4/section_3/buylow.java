package chapter_4.section_3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: buylow
 */

/**

 @author William
 */
public class buylow {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("buylow.in"));
        int N = Integer.parseInt(r.readLine());
        int prices[] = new int[N];
        StringTokenizer t = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            if (!t.hasMoreTokens()) {
                t = new StringTokenizer(r.readLine());
            }
            prices[i] = Integer.parseInt(t.nextToken());
        }
        
        int[] maxbuy = new int[N];
        BigInteger[] seqs = new BigInteger[N];
        maxbuy[N - 1] = 1;
        seqs[N - 1] = BigInteger.ONE;
        for (int i = N - 2; i >= 0; i--) {
            int cart = 1;
            BigInteger seq = BigInteger.ONE;
            boolean[] visited = new boolean[100001];
            visited[prices[i]] = true;
            for (int j = i + 1; j < N; j++) {
                if (prices[j] < prices[i]) {
                    if (visited[prices[j]]) {
                        continue;
                    }
                    visited[prices[j]] = true;
                    if (maxbuy[j] + 1 > cart) {
                        cart = maxbuy[j] + 1;
                        seq = seqs[j];
                    } else if (maxbuy[j] + 1 == cart) {
                        seq = seq.add(seqs[j]);
                    }
                }
            }
            maxbuy[i] = cart;
            seqs[i] = seq.max(BigInteger.ZERO);
        }
        /*System.out.println(Arrays.toString(prices));
        System.out.println(Arrays.toString(maxbuy));
        System.out.println(Arrays.toString(seqs));*/
        
        int max = 0;
        BigInteger seq = BigInteger.ZERO;
        boolean[] visited = new boolean[100001];
        for (int i = 0; i < N; i++) {
            if (visited[prices[i]]) {
                continue;
            }
            visited[prices[i]] = true;
            if (maxbuy[i] > max) {
                max = maxbuy[i];
                seq = seqs[i];
            } else if (maxbuy[i] == max) {
                seq = seq.add(seqs[i]);
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("buylow.out"));
        w.println(max + " " + seq);
        System.out.println(max + " " + seq);
        w.close();
    }
}
