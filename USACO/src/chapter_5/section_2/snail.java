package chapter_5.section_2;

/*
 ID: william164
 LANG: JAVA
 TASK: snail
 */
import java.io.*;
import java.util.*;

/**

 @author William
 */
public class snail {

    static boolean[][] grid;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("snail.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(t.nextToken());
        grid = new boolean[N + 2][N + 2];
        visited = new boolean[N + 2][N + 2];
        int B = Integer.parseInt(t.nextToken());
        for (int i = 0; i < B; i++) {
            String c = r.readLine();
            int x = (int) (c.charAt(0) - 'A') + 1;
            int y = Integer.parseInt(c.substring(1));
            grid[y][x] = true;
        }
        for (int i = 0; i < N + 2; i++) {
            grid[i][0] = true;
            grid[i][N + 1] = true;
            grid[0][i] = true;
            grid[N + 1][i] = true;
        }
        search(2, 1, 1);
        search(1, 1, 1);
        PrintWriter w = new PrintWriter(new FileWriter("snail.out"));
        w.println(out);
        System.out.println(out);
        w.close();
    }
    static int out = 0;
    static int count = 0;

    // 0 = left, 1 = down, 2 = right, 3 = up
    public static void search(int direction, int x, int y) {
        if (grid[y][x] || visited[y][x]) {
            out = Math.max(count, out);
            return;
        }
        count++;
        visited[y][x] = true;
        switch (direction) {
            case 0:
                if (grid[y][x - 1]) {
                    search(3, x, y - 1);
                    search(1, x, y + 1);
                }
                search(0, x - 1, y);
                break;
            case 1:
                if (grid[y + 1][x]) {
                    search(0, x - 1, y);
                    search(2, x + 1, y);
                }
                search(1, x, y + 1);
                break;
            case 2:
                if (grid[y][x + 1]) {
                    search(3, x, y - 1);
                    search(1, x, y + 1);
                }
                search(2, x + 1, y);
                break;
            case 3:
                if (grid[y - 1][x]) {
                    search(0, x - 1, y);
                    search(2, x + 1, y);
                }
                search(3, x, y - 1);
                break;
        }
        count--;
        visited[y][x] = false;
    }
}
