package chapter_1.section_3;

/*
ID: william164
LANG: JAVA
TASK: combo
*/
import java.util.*;
import java.io.*;

/**
 *
 * @author William
 */
public class combo {

    static boolean[][][] collection;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("combo.in"));

        int N = Integer.parseInt(b.readLine());

        collection = new boolean[N][N][N];

        StringTokenizer t = new StringTokenizer(b.readLine());
        int[] jcombo = {Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken())};
        t = new StringTokenizer(b.readLine());
        int[] mcombo = {Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken())};

        int number = 0;

        for (int i = -2; i <= 2; i++) {
            int one = (N + jcombo[0] + i) % N;
            for (int j = -2; j <= 2; j++) {
                int two = (N + jcombo[1] + j) % N;
                for (int l = -2; l <= 2; l++) {
                    int three = (N + jcombo[2] + l) % N;
                    if (!collection[one][two][three]) {

                        collection[one][two][three] = true;
                        number++;
                    }
                    //number++;
                }
            }
        }
        
        for (int i = -2; i <= 2; i++) {
            int one = (N + mcombo[0] + i) % N;
            for (int j = -2; j <= 2; j++) {
                int two = (N + mcombo[1] + j) % N;
                for (int l = -2; l <= 2; l++) {
                    int three = (N + mcombo[2] + l) % N;
                    if (!collection[one][two][three]) {

                        collection[one][two][three] = true;
                        number++;
                    }
                    //number++;
                }
            }
        }
        
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        p.println(number);
        p.close();
    }
}
