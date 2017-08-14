package chapter_4.section_2;


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
 TASK: job
 */
/**

 @author William
 */
public class job {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("job.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(t.nextToken()), A = Integer.parseInt(t.nextToken()), B = Integer.parseInt(t.nextToken());
        String line;
        int index = 0;
        int[] As = new int[A], Bs = new int[B];
        while ((line = r.readLine()) != null) {
            t = new StringTokenizer(line);
            while (t.hasMoreTokens()) {
                if (index < A) {
                    As[index++] = Integer.parseInt(t.nextToken());
                } else {
                    Bs[index++ - A] = Integer.parseInt(t.nextToken());
                }
            }
        }
        Arrays.sort(As);
        Arrays.sort(Bs);

        int[] AUsed = new int[A], BUsed = new int[B];

        int AO = 0, BO = 0;
        int[] itemsB = new int[N];
        for (int i = 0; i < N; i++) {
            AUsed[0] += As[0];
            AO = AUsed[0];
            int m = 0;
            while (m < As.length - 1 && AUsed[m] + As[m] > AUsed[m + 1] + As[m + 1]) {
                int temp = AUsed[m];
                AUsed[m] = AUsed[m + 1];
                AUsed[m + 1] = temp;
                temp = As[m];
                As[m] = As[m + 1];
                As[m + 1] = temp;
                m++;
            }
            itemsB[i] = AO;
        }
        
        for (int i = N - 1; i >= 0; i--) {
            BUsed[0] += Bs[0];
            BO = Math.max(BUsed[0] + itemsB[i], BO);
            int m = 0;
            while (m < Bs.length - 1 && BUsed[m] + Bs[m] > BUsed[m + 1] + Bs[m + 1]) {
                int temp = BUsed[m];
                BUsed[m] = BUsed[m + 1];
                BUsed[m + 1] = temp;
                temp = Bs[m];
                Bs[m] = Bs[m + 1];
                Bs[m + 1] = temp;
                m++;
            }
        }

        PrintWriter w = new PrintWriter(new FileWriter("job.out"));
        w.println(AO + " " + BO);
        w.close();
    }

}
