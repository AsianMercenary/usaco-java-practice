package section_2_1;


import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
import static java.lang.System.*;

/*
ID: william164
LANG: JAVA
TASK: castle
 */
/**

 @author William
 */
public class castle {

    static int M, N;
    // 0    1    2    3
    //left  up  east down
    static boolean[][][] edges;
    static boolean[][] map;
    static int rooms = 0;
    static int largestRoom = Integer.MIN_VALUE;
    static int maxRoomNoWall = Integer.MIN_VALUE;
    static String wall;

    public static void main(String[] args) throws IOException {
        BufferedReader R = new BufferedReader(new FileReader("castle.in"));
        StringTokenizer T = new StringTokenizer(R.readLine());
        M = parseInt(T.nextToken());
        N = parseInt(T.nextToken());
        edges = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            T = new StringTokenizer(R.readLine());
            for (int j = 0; j < M; j++) {
                boolean[] module = edges[i][j];

                int flags = parseInt(T.nextToken());
                if ((flags & 1) == 1) {
                    module[0] = true;
                }

                if ((flags & 2) == 2) {
                    module[1] = true;
                }

                if ((flags & 4) == 4) {
                    module[2] = true;
                }

                if ((flags & 8) == 8) {
                    module[3] = true;
                }
            }

        }

        //to find number of rooms and size of largest room
        {
            map = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j]) {
                        continue;
                    }

                    largestRoom = Math.max(largestRoom, fill(i, j));
                    rooms++;
                    //System.out.println("Room Travelled");
                }
            }
        }

        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i >= 0; i--) {
                boolean[] module = edges[i][j];
                //out.println(i + " " + j);
                //out.println(Arrays.toString(module));
                if (i > 0 && module[1]) {
                    boolean[] modN = edges[i-1][j];
                    module[1] = false;
                    modN[3] = false;
                    map = new boolean[N][M];
                    int roomsize = findLargestRoom();
                    //out.println(roomsize);
                    //out.println((i + 1) + " " + (j + 1) + " N\n");
                    if (roomsize > maxRoomNoWall) {
                        maxRoomNoWall = roomsize;
                        wall = (i + 1) + " " + (j + 1) + " N";
                    }
                    module[1] = true;
                    modN[3] = true;
                }

                if (j < M - 1 && module[2]) {
                    boolean[] modE = edges[i][j+1];
                    module[2] = false;
                    modE[0] = false;
                    map = new boolean[N][M];
                    int roomsize = findLargestRoom();
                    //out.println(roomsize);
                    //out.println((i + 1) + " " + (j + 1) + " E\n");
                    if (roomsize > maxRoomNoWall) {
                        maxRoomNoWall = roomsize;
                        wall = (i + 1) + " " + (j + 1) + " E";
                    }
                    module[2] = true;
                    modE[0] = true;
                }
            }
        }

        PrintWriter p = new PrintWriter(new FileWriter("castle.out"));
        p.println(rooms);
        p.println(largestRoom);
        p.println(maxRoomNoWall);
        p.println(wall);
        //out.println(rooms);
        //out.println(largestRoom);
        //out.println(maxRoomNoWall);
        //out.println(wall);
        p.close();
    }

    public static int findLargestRoom() {
        int maxRoom = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    continue;
                }
                maxRoom = Math.max(maxRoom, fill(i, j));
            }
        }
        return maxRoom;
    }

    public static int fill(int i, int j) {
        if (map[i][j]) {
            return 0;
        }

        map[i][j] = true;

        //System.out.println(Arrays.toString(edges[i][j]));

        /*{
            out.println(i + " " + j);
            for (boolean[] a : map) {
                for (boolean b : a) {
                    if (b) {
                        out.print("X ");
                    } else {
                        out.print("O ");
                    }
                }
                out.println();
            }
            out.println();
        }*/

        int count = 1;
        if (!edges[i][j][0]) {
            count += fill(i, j - 1);
        }
        if (!edges[i][j][1]) {
            count += fill(i - 1, j);
        }
        if (!edges[i][j][2]) {
            count += fill(i, j + 1);
        }
        if (!edges[i][j][3]) {
            count += fill(i + 1, j);
        }
        return count;
    }
}
