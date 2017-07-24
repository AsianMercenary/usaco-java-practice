package chapter_3.section_3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: camelot
 */
/**
 *
 * @author William
 */
public class camelot {

    static int R, C;
    static int kr, kc;
    static int[] p;
    static int[][] dists;
    static int[][] kingDists;
    static int[] kingMoves;
    static int[] kingloc;
    static int[] tested;
    static boolean[] kingTested;
    static int size, kingSize;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("camelot.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        R = Integer.parseInt(t.nextToken());
        C = Integer.parseInt(t.nextToken());

        t = new StringTokenizer(r.readLine());
        kc = t.nextToken().charAt(0) - 'A';
        kr = R - Integer.parseInt(t.nextToken());

        p = new int[R * C];

        if (t.hasMoreTokens()) {
            int hc = t.nextToken().charAt(0) - 'A', hr = R - Integer.parseInt(t.nextToken());
            p[size++] = cti(hr, hc);
        }
        String line;
        while ((line = r.readLine()) != null) {
            t = new StringTokenizer(line);
            while (t.hasMoreTokens()) {
                int hc = t.nextToken().charAt(0) - 'A', hr = R - Integer.parseInt(t.nextToken());
                p[size++] = cti(hr, hc);
            }
        }

        tested = new int[R * C];
        dists = new int[size][R * C];
        Arrays.fill(tested, -1);
        for (int i = 0; i < size; i++) {
            if (tested[p[i]] != -1) {
                continue;
            }
            tested[p[i]] = i;
            dists[i] = sp(p[i]);
        }

        kingDists = new int[R * C][];
        kingloc = new int[R * C];
        kingMoves = new int[R * C];
        kingTested = new boolean[R * C];

        int kpos = cti(kr, kc);
        int mdist = Integer.MAX_VALUE;
        int msq = -1;
        for (int s = 0; s < R * C; s++) {
            int dist = 0;
            int[] pos = itc(s);
            int dx = Math.abs(pos[1] - kc);
            int dy = Math.abs(pos[0] - kr);
            int minKingDist = dx + dy - Math.min(dx, dy);
            for (int h = 0; h < size; h++) {
                if (dists[h][s] == Integer.MAX_VALUE) {
                    dist = Integer.MAX_VALUE;
                    break;
                } else {
                    dist += dists[h][s];
                }
            }

            if (dist == Integer.MAX_VALUE) {
                continue;
            }

            if (mdist > dist + minKingDist) {
                mdist = dist + minKingDist;
                msq = s;
            }

            for (int h = 0; h < size; h++) {
                int currentindex = 0;
                ArrayList<Integer> current = new ArrayList<>();
                current.add(kpos);

                int ndist = Integer.MAX_VALUE;
                for (int m = 0; m < minKingDist && m <= 2; m++) {
                    while (!current.isEmpty()) {
                        int[] knp = itc(current.remove(0));
                        //System.out.println((char) (knp[1] - 'A') + " " + (R - knp[0]));
                        move(knp[0], knp[1], m);
                    }
                    //System.out.println();
                    current = queue;
                    queue = new ArrayList<>();
                    while (currentindex < kingSize) {
                        int loc = kingloc[currentindex], findist = kingDists[currentindex][s];
                        int d = kingMoves[currentindex];

                        currentindex++;

                        if (dists[h][loc] == Integer.MAX_VALUE || findist == Integer.MAX_VALUE) {
                            continue;
                        } else {
                            d += dists[h][loc] + findist;
                        }

                        ndist = Math.min(ndist, dist + d - dists[h][s]);
                    }
                }

                if (mdist > ndist) {
                    mdist = ndist;
                    msq = s;
                }

            }
        }
        int[] pos = itc(msq);
        System.out.println((char) (pos[1] + 'A') + " " + (R - pos[0]));

        PrintWriter p = new PrintWriter(new FileWriter("camelot.out"));
        p.println(mdist);
        p.close();
    }

    static int[][] moves = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}};

    static ArrayList<Integer> queue = new ArrayList<>();

    static void move(int kr, int kc, int moves) {
        int asint = cti(kr, kc);
        if (!kingTested[asint]) {
            kingloc[kingSize] = asint;

            if (tested[asint] != -1) {
                kingDists[kingSize] = dists[tested[asint]];
            } else {
                kingDists[kingSize] = sp(asint);
            }
            kingTested[asint] = true;
            queue.add(asint);
            kingMoves[kingSize] = moves;
            kingSize++;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int row = kr + i, col = kc + j;
                if (row < 0 || row >= R || col < 0 || col >= C) {
                    continue;
                }
                asint = cti(row, col);
                if (!kingTested[asint]) {
                    queue.add(asint);
                }

                //System.out.println(Arrays.toString(kingDists[kingSize]));
            }
        }
    }

    static int[] sp(int k) {
        int[] dist = new int[R * C];
        boolean[] visited = new boolean[R * C];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[o2];
            }

        });
        q.add(k);
        loop:
        for (int s = 0; s < R * C; s++) {
            int m;
            do {
                if (q.isEmpty()) {
                    break loop;
                }
                m = q.poll();
            } while (visited[m]);

            visited[m] = true;

            int[] c = itc(m);

            for (int n = 0; n < 8; n++) {
                int i = c[0] + moves[n][0];
                int j = c[1] + moves[n][1];
                if (i < 0 || i >= R || j < 0 || j >= C) {
                    continue;
                }

                int nb = cti(i, j);
                if (dist[nb] > dist[m] + 1) {
                    dist[nb] = dist[m] + 1;
                    q.add(nb);
                }
            }
        }
        return dist;
    }

    static int cti(int i, int j) {
        return i * C + j;
    }

    static int[] itc(int i) {
        return new int[]{i / C, i % C};
    }
}
