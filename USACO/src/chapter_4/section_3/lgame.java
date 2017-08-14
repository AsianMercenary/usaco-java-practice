package chapter_4.section_3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 ID: william164
 LANG: JAVA
 TASK: lgame
 */
/**

 @author William
 */
public class lgame {

    static int[] values
            = {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};

    public static void main(String[] args) throws IOException {
        char[] words = new BufferedReader(new FileReader("lgame.in")).readLine().toCharArray();
        int[] letters = new int[26];
        for (char c : words) {
            letters[c - 'a']++;
        }
        BufferedReader r = new BufferedReader(new FileReader("lgame.dict"));
        String[] dict = new String[20000];
        String line;
        int index = 0;
        while (!(line = r.readLine()).equals(".")) {
            char[] word = line.toCharArray();
            int[] consists = new int[26];
            boolean possible = true;
            for (char c : word) {
                if (letters[c - 'a'] < ++consists[c - 'a']) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                dict[index++] = line;
            }
        }

        ArrayList<String> output = new ArrayList<String>();
        int max = 0;
        for (int i = 0; i < index; i++) {
            String si = "";
            int scorei = 0;
            int[] used = new int[26];
            for (char c : dict[i].toCharArray()) {
                used[c - 'a']++;
                scorei += values[c - 'a'];
            }
            si += dict[i];
            if (scorei > max) {
                max = scorei;
                output = new ArrayList<>();
                output.add(si);
            } else if (scorei == max) {
                output.add(si);
            }
            
            for (int j = i + 1; j < index; j++) {
                String sj = si;
                int scorej = scorei;
                int[] consists = new int[26];
                boolean possible = true;
                for (char c : dict[j].toCharArray()) {
                    if (letters[c - 'a'] < used[c - 'a'] + ++consists[c - 'a']) {
                        possible = false;
                        break;
                    }
                    scorej += values[c - 'a'];
                }

                if (possible) {
                    sj += " " + dict[j];
                    if (scorej > max) {
                        max = scorej;
                        output = new ArrayList<>();
                        output.add(sj);
                    } else if (scorej == max) {
                        output.add(sj);
                    }
                }
            }
        }
        PrintWriter w = new PrintWriter(new FileWriter("lgame.out"));
        w.println(max);
        for (String s : output) {
            w.println(s);
        }
        w.close();
    }
}
