package chapter_1.section_3;


import java.util.*;
import java.io.*;

/*
ID: william164
LANG: JAVA
TASK: wormhole
 */

/**
 *
 * @author William
 */
public class wormhole {

    static coordinate[] coordinates;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("wormhole.in"));
        int N = Integer.parseInt(b.readLine());
        coordinates = new coordinate[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer t = new StringTokenizer(b.readLine());
            int xCoord = Integer.parseInt(t.nextToken());
            int yCoord = Integer.parseInt(t.nextToken());
            coordinates[i] = new coordinate(xCoord, yCoord);
        }


        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        p.println(run(0, new int[N], new boolean[N]));
        p.close();
    }

    public static int run(int index, int[] pairs, boolean[] paired) {
        if (index >= pairs.length) {
            //System.out.println(Arrays.toString(pairs));
            
            if (isCircular(pairs)) {
                return 1;
            }
            //System.out.println("failed");
            return 0;

        }

        if (paired[index]) {
            return run(index + 1, pairs, paired);
        }

        int sum = 0;
        for (int i = 0; i < coordinates.length; i++) {
            if (!paired[i] && i != index) {
                int[] newPairs = (int[]) pairs.clone();
                boolean[] newPaired = (boolean[]) paired.clone();
                newPairs[index] = i;
                newPairs[i] = index;
                newPaired[index] = true;
                newPaired[i] = true;
                sum += run(index + 1, newPairs, newPaired);

            }
        }
        return sum;
    }

    public static boolean isCircular(int[] pairs) {
        for (int i = 0; i < pairs.length; i++) {
            boolean[] tested = new boolean[pairs.length];
            int oldIndex = i;
            while (true) {
                int newIndex = findNext(oldIndex);
                //System.out.println("Moved from: " + oldIndex + " to " + newIndex);
                if (newIndex == -1) {
                    break;
                }
                if (tested[newIndex]) {
                    return true;
                }

                tested[newIndex] = true;

                oldIndex = pairs[newIndex];
                //System.out.println("entered the portal to get to: " + oldIndex);
            }
        }
        return false;
    }

    public static int findNext(int index) {
        coordinate c = coordinates[index];
        int mindist = Integer.MAX_VALUE;
        int record = -1;
        for (int i = 0; i < coordinates.length; i++) {
            if (i == index) {
                continue;
            }
            coordinate temp = coordinates[i];
            if (temp.y != c.y || temp.x - c.x > mindist || temp.x < c.x) {
                continue;
            }
            record = i;
            mindist = temp.x - c.x;

        }
        return record;
    }
}

class coordinate {

    int x;
    int y;

    public coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
