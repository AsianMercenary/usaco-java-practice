package chapter_1.section_3;


import java.io.*;
import java.util.Arrays;

/*
ID: william164
LANG: JAVA
TASK: skidesign
 */
/**
 *
 * @author William
 */
public class skidesign {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("skidesign.in"));
        int N = Integer.parseInt(b.readLine().trim());
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        int[] hills = new int[N];
        for (int i = 0; i < N; i++) {
            hills[i] = Integer.parseInt(b.readLine().trim());
            max = Math.max(hills[i], max);
            min = Math.min(hills[i], min);
        }

        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        
        int lowest = Integer.MAX_VALUE;
        for (int i = min; i <= max - 17; i++) {
            int compare = 0;
            for (int j : hills) {
                if (j < i) {
                    compare += Math.pow(i - j, 2);
                } else if (j > i + 17) {
                    compare += Math.pow(j - (17 + i), 2);
                }
            }
            lowest = Math.min(lowest, compare);
        }
        

        p.println(lowest);
        p.close();
    }
}
