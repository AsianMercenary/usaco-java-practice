package chapter_2.section_4;

/*
 ID: william164
 LANG: JAVA
 TASK: maze1
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

/**

 @author William
 */
public class maze1 {

    static int W, H;

    static Digit[][] maze;

    static int nodesVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("maze1.in"));
        StringTokenizer t = new StringTokenizer(b.readLine());
        W = parseInt(t.nextToken());
        H = parseInt(t.nextToken());

        maze = new Digit[H + 2][W + 2];
        for (Digit[] n : maze) {
            for (int i = 0; i < n.length; i++) {
                n[i] = new Digit();
            }
        }

        for (int i = 0; i < H * 2 + 1; i++) {
            String line = b.readLine();
            if (i % 2 == 0) {
                for (int j = 1; j < W * 2 + 1; j += 2) {
                    if (line.charAt(j) == ' ') {
                        maze[i / 2][j / 2 + 1].south = true;
                        maze[i / 2 + 1][j / 2 + 1].north = true;
                    }
                }
            } else {
                for (int j = 0; j < W * 2 + 1; j += 2) {
                    if (line.charAt(j) == ' ') {
                        maze[i / 2 + 1][j / 2].east = true;
                        maze[i / 2 + 1][j / 2 + 1].west = true;
                    }
                }
            }
        }

        for (Digit n : maze[0]) {
            n.distance = 0;
        }

        for (Digit n : maze[maze.length - 1]) {
            n.distance = 0;
        }

        for (int i = 1; i < maze.length - 1; i++) {
            maze[i][0].distance = 0;
            maze[i][maze[i].length - 1].distance = 0;
        }
        
        

        nodesVisited = 0;
        while (nodesVisited < (W + 2) * (H + 2)) {
            int min = Integer.MAX_VALUE;
            int lowx = -1, lowy = -1;
            for (int y = 0; y < maze.length; y++) {
                for (int x = 0; x < maze[y].length; x++) {
                    if (!maze[y][x].visited && maze[y][x].distance < min) {
                        min = maze[y][x].distance;
                        lowx = x;
                        lowy = y;
                    }
                }
            }

            Digit i = maze[lowy][lowx];
            i.visited = true;

            if (i.east) {
                if (i.distance + 1 < maze[lowy][lowx + 1].distance) {
                    maze[lowy][lowx + 1].distance = i.distance + 1;
                }
            }
            if (i.west) {
                if (i.distance + 1 < maze[lowy]
                        [lowx - 1].distance) {
                    maze[lowy][lowx - 1].distance = i.distance + 1;
                }
            }
            if (i.north) {
                if (i.distance + 1 < maze[lowy - 1][lowx].distance) {
                    maze[lowy - 1][lowx].distance = i.distance + 1;
                }
            }
            if (i.south) {
                if (i.distance + 1 < maze[lowy + 1][lowx].distance) {
                    maze[lowy + 1][lowx].distance = i.distance + 1;
                }
            }

            nodesVisited++;
        }
        /*
        for (Digit[] n : maze) {
            for (Digit node : n) {
                System.out.print(node.north + " ");
            }
            System.out.println();
        } */

        int max = Integer.MIN_VALUE;

        for (Digit[] nodes : maze) {
            for (Digit n : nodes) {
                max = Math.max(max, n.distance);
            }
        }

        PrintWriter p = new PrintWriter(new FileWriter("maze1.out"));
        p.println(max);
        //System.out.println(max);
        p.close();
    }
}

class Digit {

    int distance = Integer.MAX_VALUE;
    boolean visited = false;
    boolean north = false, south = false, east = false, west = false;
}
