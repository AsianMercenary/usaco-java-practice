package chapter_5.section_1;

/*
 ID: william164
 LANG: JAVA
 TASK: theme
 */
import java.io.*;
import java.util.StringTokenizer;

/**

 @author William
 */
public class theme {

    static int N;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("theme.in"));
        N = Integer.parseInt(r.readLine());
        seq = new int[N];
        int index = 0;
        String line;
        while ((line = r.readLine()) != null) {
            StringTokenizer t = new StringTokenizer(line);
            while (t.hasMoreTokens()) {
                seq[index++] = Integer.parseInt(t.nextToken());
            }
        }
        int longest = 0;
        for (int i = 0; i < N - longest * 2 - 1; i++) {
            for (int j = i + 1; j < N - longest; j++) {
                int k = 0;
                int modifier = seq[j] - seq[i];
                while (j + k < N && seq[j + k] == seq[i + k] + modifier && i + k < j) {
                    k++;
                }
                if (k >= 5) {
                    longest = Math.max(longest, k);
                }
            }
        }
        System.out.println(longest);
        PrintWriter w = new PrintWriter(new FileWriter("theme.out"));
        w.println(longest);
        w.close();
    }
}
