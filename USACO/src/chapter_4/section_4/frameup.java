package chapter_4.section_4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: frameup
 */
/**

 @author William
 */
public class frameup {

    static int H, W;
    static int letters = 0;
    static char[][] end;
    static boolean[] exists = new boolean[26];
    static int[] leftx = new int[26], lefty = new int[26], rightx = new int[26], righty = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("frameup.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        H = Integer.parseInt(t.nextToken());
        W = Integer.parseInt(t.nextToken());
        end = new char[H][W];
        for (int i = 0; i < H; i++) {
            char[] line = r.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (line[j] == '.') {
                    end[i][j] = ' ';
                    continue;
                }
                end[i][j] = line[j];
                int index = line[j] - 'A';
                if (!exists[index]) {
                    leftx[index] = rightx[index] = j;
                    lefty[index] = righty[index] = i;
                    letters++;
                }
                leftx[index] = Math.min(j, leftx[index]);
                rightx[index] = Math.max(j, rightx[index]);
                lefty[index] = Math.min(i, lefty[index]);
                righty[index] = Math.max(i, righty[index]);
                exists[index] = true;
            }
        }

        search(0, new boolean[26]);
        Collections.sort(output);
        PrintWriter w = new PrintWriter(new FileWriter("frameup.out"));
        for (String s : output) {
            w.println(s);
        }
        w.close();
    }

    static ArrayList<String> output = new ArrayList<>();
    static String path = "";

    public static void search(int count, boolean[] visited) {
        if (count == letters) {
            output.add(path);
            return;
        }
        loopa:
        for (int i = 25; i >= 0; i--) {
            if (!visited[i] && exists[i]) {
                for (int j = leftx[i]; j <= rightx[i]; j++) {
                    if (!visited[end[lefty[i]][j] - 'A'] && end[lefty[i]][j] - 'A' != i) {
                        continue loopa;
                    }
                    if (!visited[end[righty[i]][j] - 'A'] && end[righty[i]][j] - 'A' != i) {
                        continue loopa;
                    }
                }

                for (int j = lefty[i] + 1; j < righty[i]; j++) {
                    if (!visited[end[j][leftx[i]] - 'A'] && end[j][leftx[i]] - 'A' != i) {
                        continue loopa;
                    }
                    if (!visited[end[j][rightx[i]] - 'A'] && end[j][rightx[i]] - 'A' != i) {
                        continue loopa;
                    }
                }
                visited[i] = true;
                path = (char) (i + 'A') + path;
                search(count + 1, visited);
                visited[i] = false;
                path = path.substring(1);
            }
        }
    }

}
