package chapter_2.section_2;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: subset
 */
/**

 @author William
 */
public class subset {

    static double match;
    static int n;
    static int[][] library;
    static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(new BufferedReader(new FileReader("subset.in")).readLine());
        int sum = n * (n + 1) / 2;
        match = (double) sum / 2;
        
        library = new int[n + 1][(int) Math.ceil(match) + 1];
        checked = new boolean[n + 1][(int) Math.ceil(match) + 1];
        

        int count = partition(n, 0);

        //System.out.println(Arrays.toString(library));
        PrintWriter p = new PrintWriter(new FileWriter("subset.out"));
        p.println(count);
        System.out.println(count);
        p.close();
    }

    public static int partition(int index, int sum) {
        if (index == 0 || sum > match) {
            return 0;
        }

        if (match - sum > index * (index + 1) / 2) {
            return 0;
        }

        if (sum == match) {
            return 1;
        }

        if (!checked[index][sum]) {
            int a = 0, b = 0;
            a = partition(index - 1, sum + index);
            b = partition(index - 1, sum);
            library[index][sum] = a + b;
            checked[index][sum] = true;
        }
        return library[index][sum];
    }
}
