package chapter_3.section_1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: humble
 */
/**

 @author William
 */
public class humble {

    static int size;
    static long[] nodes;
    static int N, K;
    static long prev;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("humble.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        K = Integer.parseInt(t.nextToken());
        N = Integer.parseInt(t.nextToken());
        t = new StringTokenizer(r.readLine());
        int[] factors = new int[K];
        for (int i = 0; i < K; i++) {
            factors[i] = Integer.parseInt(t.nextToken());
        }

        nodes = new long[10 * N];
        size = 1;
        nodes[0] = 1;
        prev = 0L;
        
        for (int i = 0; i <= N; i++) {
            //System.out.println(Arrays.toString(nodes));
            long min;
            while (nodes[0] <= prev && size > 1) {
                remove();
            }
            min = remove();
            prev = min;
            for (int j : factors) {
                insert(min * j);
            }
        }

        System.out.println(prev);
        PrintWriter p = new PrintWriter(new FileWriter("humble.out"));
        p.println(prev);
        p.close();
    }

    public static void insert(long v) {
        if (size >= nodes.length - 1) {
            long[] temp = new long[nodes.length];
            //System.out.println();
            int tsize;
            for (tsize = 0; size > 0 && tsize < N; tsize++) {
                long l = remove();
                if (l <= prev || (tsize > 0 && l <= temp[tsize - 1])) {
                    tsize--;
                } else {
                    temp[tsize] = l;
                }
                //System.out.println(l);
            }
            nodes = temp;
            size = tsize;
            //System.out.println("\n" + Arrays.toString(nodes) + "\n");
        }
        
        int i = size;
        nodes[size++] = v;
        int p = (i - 1) / 2;
        while (p >= 0) {
            if (nodes[p] > nodes[i]) {
                long temp = nodes[p];
                nodes[p] = nodes[i];
                nodes[i] = temp;
                i = p;
                p = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    public static long remove() {
        long out = nodes[0];
        nodes[0] = 0;
        nodes[0] = nodes[--size];
        int left = 1, right = 2;
        int v = 0;
        while (left < size) {
            if (right < size && nodes[right] < nodes[left]) {
                if (nodes[v] > nodes[right]) {
                    long temp = nodes[right];
                    nodes[right] = nodes[v];
                    nodes[v] = temp;
                    v = right;
                    left = (v + 1) * 2 - 1;
                    right = (v + 1) * 2;
                } else {
                    break;
                }
            } else if (nodes[v] > nodes[left]) {
                long temp = nodes[left];
                nodes[left] = nodes[v];
                nodes[v] = temp;
                v = left;
                left = (v + 1) * 2 - 1;
                right = (v + 1) * 2;
            } else {
                break;
            }
        }
        return out;
    }
}
