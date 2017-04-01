package chapter_1.section_5;

/*
ID: william164
LANG: JAVA
TASK: numtri
 */
import java.io.*;
import static java.lang.Integer.*;
import java.util.StringTokenizer;

/**
 *
 * @author William
 */
public class numtri {

    static int[][] tree;
    static int[][] values;
    static int rows;
    static int output = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader R = new BufferedReader(new FileReader("numtri.in"));
        rows = parseInt(R.readLine());
        tree = new int[rows][];
        values = new int[rows + 1][];
        for (int row = 0; row < rows; row++) {
            StringTokenizer T = new StringTokenizer(R.readLine());

            tree[row] = new int[row + 1];
            values[row] = new int[row + 1];
            for (int i = 0; i < row + 1; i++) {
                tree[row][i] = parseInt(T.nextToken());
            }
        }
        values[rows] = new int[rows + 1];
        for (int row = rows - 1; row >= 0; row--) {
            for (int i = 0; i < row + 1; i++) {
                int currentVal = tree[row][i];
                int maxNext = Math.max(values[row + 1][i], values[row + 1][i + 1]);
                values[row][i] = currentVal + maxNext;
            }
        }
        
        output = values[0][0];

        PrintWriter p = new PrintWriter(new FileWriter("numtri.out"));
        p.println(output);
        System.out.println(output);
        p.close();
    }
}
