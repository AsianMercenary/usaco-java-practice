package chapter_2.section_3;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: nocows
 */
/**

 @author William
 */
public class nocows {
    //height

    static int N, K;
    static int[] before, after;
    static long[][] lib;
    static long[][][] lib2;

    public static void main(String[] args) throws IOException {
        StringTokenizer T = new StringTokenizer(new BufferedReader(new FileReader("nocows.in")).readLine());
        N = Integer.parseInt(T.nextToken());
        K = Integer.parseInt(T.nextToken());
        lib = new long[N + 1][N + 1];
        lib2 = new long[N + 1][K + 1][N + 1];

        lib[0][0] = 1;

        for (int i = 1; i <= N; i++) {
            lib[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                lib[i][j] = lib[i - 1][j] + lib[i - 1][j - 1];
            }
        }

        for (long[][] i : lib2) {
            for (long[] j : i) {
                for (int a = 0; a < j.length; a++) {
                    j[a] = -1;
                }
            }
        }

        /*
         for (long[] i : lib) {
         System.out.println(Arrays.toString(i));
         }
         */
        long output = search(1);

        output %= 9901;
        PrintWriter p = new PrintWriter(new FileWriter("nocows.out"));
        p.println(output);
        System.out.println(output);
        p.close();
    }

    static int lvl = 1;
    static int count = 1;

    public static long search(int pts) {
        /*
         System.out.println(count + " " + lvl);
         for (int[] i : lib) {
         System.out.println(Arrays.toString(i));
         }
         */
        if (count == N && lvl == K) {
            return 1;
        } else if (count > N || lvl > K) {
            return 0;
        } else if (count - 2 * pts * (1 - Math.pow(2, K - lvl)) < N || lvl + N - count < K) {
            return 0;
        }

        if (lib2[pts][lvl][count] == -1) {
            long out = 0;
            for (int i = 1; i <= pts; i++) {
                lvl++;
                count += i * 2;
                out += lib[pts][i] * search(i * 2);
                lvl--;
                count -= i * 2;
            }
            lib2[pts][lvl][count] = out;
        }
        return lib2[pts][lvl][count] % 9901;
    }
}
