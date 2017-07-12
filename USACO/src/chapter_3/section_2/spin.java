package chapter_3.section_2;

/*
 ID: william164
 LANG: JAVA
 TASK: spin
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

 @author William
 */
public class spin {
    static int[] speeds = new int[5];
    static int[] position = new int[5];
    static boolean[][] wedges = new boolean[5][360];
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("spin.in"));
        for(int i = 0; i < 5; i++) {
            StringTokenizer t = new StringTokenizer(r.readLine());
            speeds[i] = Integer.parseInt(t.nextToken());
            int w = Integer.parseInt(t.nextToken());
            for (int j = 0; j < w; j++) {
                int start = Integer.parseInt(t.nextToken());
                int length = Integer.parseInt(t.nextToken());
                for (int k = 0; k <= length; k++) {
                    wedges[i][359 -(start + k) % 360] = true;
                }
            }
        }
        
        int o;
        loop:
        for (o = 0; o < 360; o++) {
            /*for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 360; j++) {
                    System.out.print(wedges[i][(position[i] + j) % 360] + " ");
                }
                System.out.println();
            }
            System.out.println();*/
            for (int i = 0; i < 360; i++) {
                boolean hole = true;
                for (int j = 0; j < 5; j++) {
                    hole = hole && wedges[j][(position[j] + i) % 360];
                }
                if (hole) {
                    break loop;
                }
            }
            
            for (int i = 0; i < 5; i++) {
                position[i] += speeds[i];
            }
        }
        
        PrintWriter p = new PrintWriter(new FileWriter("spin.out"));
        if (o == 360) {
            p.println("none");
            System.out.println("none");
        } else {
            p.println(o);
            System.out.println(o);
        }
        p.close();
    }
}
