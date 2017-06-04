package chapter_2.section_3;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: prefix
 */
/**

 @author William
 */
public class prefix {

    static ArrayList<String> dict = new ArrayList<>();

    static char[] letters = new char[200000];
    static int lSize = 0;
    static boolean[] searched = new boolean[200000];
    
    static ArrayList<Integer> queue = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("prefix.in"));

        String line;
        while (!(line = b.readLine()).trim().equals(".")) {
            StringTokenizer t = new StringTokenizer(line);

            while (t.hasMoreTokens()) {
                dict.add(t.nextToken());
            }
        }

        
        while ((line = b.readLine()) != null) {
            for (char c : line.trim().toCharArray()) {
                letters[lSize++] = c;
            }
        }

        int maxsize = 0;
        queue.add(0);
        while (!queue.isEmpty()) {
            int current = queue.get(0);
            maxsize = Math.max(maxsize, current);
            search(current);
            queue.remove(0);
        }
        //System.out.println(Arrays.toString(count));
        PrintWriter p = new PrintWriter(new FileWriter("prefix.out"));
        p.println(maxsize);
        System.out.println(maxsize);
        p.close();

    }

    public static void search(int index) {
        if (searched[index]) {
            return;
        }

        if (index >= lSize) {
            return;
        }

        searched[index] = true;

        int maxLength = 0;

        nextPrim:
        for (String s : dict) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] != letters[index + i]) {
                    c = null;
                    continue nextPrim;
                }
            }
            c = null;
            queue.add(s.length() + index);
        }
    }

}
