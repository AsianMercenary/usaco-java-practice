package chapter_2.section_4;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: ttwo
 */
/**

 @author William
 */
public class ttwo {

    static boolean[][] obs = new boolean[10][10];
    static boolean[] lib = new boolean[0b11111001100110011001];

    static int Cx, Cy;
    static int Fx, Fy;
    static int Cd, Fd;
    static final int N = 0, E = 1, S = 2, W = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("ttwo.in"));
        for (int i = 0; i < 10; i++) {
            int j = 0;
            char[] line = b.readLine().toCharArray();
            for (char c : line) {
                switch (c) {
                    case '*':
                        obs[i][j] = true;
                        break;
                    case 'C':
                        Cx = j;
                        Cy = i;
                        break;
                    case 'F':
                        Fx = j;
                        Fy = i;
                        break;
                }
                j++;
            }
        }
        Cd = N;
        Fd = N;

        PrintWriter p = new PrintWriter(new FileWriter("ttwo.out"));

        int min = 0;
        mloop:
        while (true) {
            int status = Cd;
            status <<= 2;
            status += Fd;
            status <<= 4;
            status += Cx;
            status <<= 4;
            status += Cy;
            status <<= 4;
            status += Fx;
            status <<= 4;
            status += Fy;
            //System.out.println(status);
            //System.out.println(Cd + " " + Fd + " " + Cx + " " + Cy + " " + Fx + " " + Fy);

            if (lib[status]) {
                p.println(0);
                p.close();
                break;
            }

            lib[status] = true;

            {
                int newx = Cx, newy = Cy;
                switch (Cd) {
                    case N:
                        newy--;
                        break;
                    case S:
                        newy++;
                        break;
                    case E:
                        newx++;
                        break;
                    case W:
                        newx--;
                        break;
                }

                if (newx < 0 || newx > 9 || newy < 0 || newy > 9 || obs[newy][newx]) {
                    Cd++;
                    Cd %= 4;
                } else {
                    Cx = newx;
                    Cy = newy;
                }
            }

            {
                int newx = Fx, newy = Fy;
                switch (Fd) {
                    case N:
                        newy--;
                        break;
                    case S:
                        newy++;
                        break;
                    case E:
                        newx++;
                        break;
                    case W:
                        newx--;
                        break;
                }

                if (newx < 0 || newx > 9 || newy < 0 || newy > 9 || obs[newy][newx]) {
                    Fd++;
                    Fd %= 4;
                } else {
                    Fx = newx;
                    Fy = newy;
                }
            }

            min++;
            if (Cx == Fx && Cy == Fy) {
                p.println(min);
                p.close();
                break;
            }

        }

    }
}
