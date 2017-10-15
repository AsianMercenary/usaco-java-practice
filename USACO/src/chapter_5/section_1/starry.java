package chapter_5.section_1;


import java.io.*;

/*
 ID: william164
 LANG: JAVA
 TASK: starry
 */
/**

 @author William
 */
public class starry {

    static boolean[][] stars;
    static char[][] fin;
    static int W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("starry.in"));
        W = Integer.parseInt(r.readLine());
        H = Integer.parseInt(r.readLine());
        stars = new boolean[H][W];
        boolean[][] visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            char[] c = r.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                stars[i][j] = c[j] == '1';
            }
        }
        fin = new char[H][W];
        Cluster[] clusters = new Cluster[26];
        int size = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!stars[i][j]) {
                    continue;
                }
                graph = new boolean[H][W];
                minX = Integer.MAX_VALUE;
                minY = Integer.MAX_VALUE;
                maxX = Integer.MIN_VALUE;
                maxY = Integer.MIN_VALUE;
                count = 0;
                fill(j, i);
                boolean[][] map = new boolean[maxY - minY + 1][maxX - minX + 1];
                for (int a = minY; a <= maxY; a++) {
                    for (int b = minX; b <= maxX; b++) {
                        //System.out.print((graph[a][b] ? 1 : 0));
                        map[a - minY][b - minX] = graph[a][b];
                    }
                    //System.out.println();
                }
                Cluster n = new Cluster(count, map);
                int found = -1;
                for (int a = 0; a < size; a++) {
                    if (clusters[a].equal(n)) {
                        found = a;
                        break;
                    }
                }
                if (found == -1) {
                    //System.out.println("new");
                    found = size;
                    clusters[size] = n;
                    size++;
                }
                //System.out.println();
                char name = (char) ('a' + found);
                for (int a = minY; a <= maxY; a++) {
                    for (int b = minX; b <= maxX; b++) {
                        if (graph[a][b]) {
                            fin[a][b] = name;
                        }
                    }
                }
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("starry.out"));
        for (char[] c : fin) {
            String line = "";
            for (char a : c) {
                if (a < 'a') {
                    line += '0';
                } else {
                    line += a;
                }
            }
            w.println(line);
            //System.out.println(line);
        }
        w.close();
    }

    static boolean[][] graph;
    static int minX, minY, maxX, maxY;
    static int count;

    static void fill(int x, int y) {
        if (x < 0 || y < 0 || x >= W || y >= H) {
            return;
        }
        if (stars[y][x]) {
            graph[y][x] = true;
            stars[y][x] = false;
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            count++;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    fill(x + i, y + j);
                }
            }
        }
    }

}

class Cluster {

    int count;
    boolean[][] map;

    Cluster(int count, boolean[][] map) {
        this.count = count;
        this.map = map;
    }

    public boolean equal(Cluster b) {
        boolean[][] bmap = b.map;
        if (b.count != count) {
            return false;
        }
        if (map.length == bmap.length && map[0].length == bmap[0].length) {
            boolean match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[i][j]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
            match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[i][map[i].length - j - 1]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
            match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[map.length - i - 1][map[i].length - j - 1]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
            match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[map.length - i - 1][j]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
        }
        if (map.length == bmap[0].length && map[0].length == bmap.length) {
            boolean match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[j][i]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
            match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[map[i].length - j - 1][i]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
            match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[map[i].length - j - 1][map.length - i - 1]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
            match = true;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != bmap[j][map.length - i - 1]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
}
