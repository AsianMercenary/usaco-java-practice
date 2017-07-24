package chapter_3.section_3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: william164
LANG: JAVA
TASK: range
 */

/**
 *
 * @author William
 */
public class range {
    
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("range.in"));
        int N = Integer.parseInt(r.readLine());
        boolean[][] map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = r.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = "1".equals(line[j]);
            }
        }
        
        PrintWriter p = new PrintWriter(new FileWriter("range.out"));
        
        boolean[][] exists = map.clone();
        boolean[][] newExists = new boolean[N][N];
        for (int size = 2; size <= N; size++) {
            int count = 0;
            for (int i = 0; i <= N - size; i++) {
                int testI = i + size - 1;
                for (int j = 0; j <= N - size; j++) {
                        int testJ = j + size - 1;
                        if (exists[i][j] && exists[i][j+1] && exists[i+1][j] && map[testI][testJ]) {
                            newExists[i][j] = true;
                            count++;
                        }
                }
            }
            if (count == 0) {
                break;
            }
            exists = newExists;
            newExists = new boolean[N][N];
            p.println(size + " " + count);
        }
        p.close();
    }
}
