package chapter_1.section_3;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: barn1
*/

/**
 *
 * @author William
 */
public class barn1 {
    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("barn1.in"));
        StringTokenizer t = new StringTokenizer(b.readLine());
        int M = Integer.parseInt(t.nextToken()),S = Integer.parseInt(t.nextToken()),C = Integer.parseInt(t.nextToken());
        
        boolean[] stalls = new boolean[S];
        
        int lowest = Integer.MAX_VALUE, highest = Integer.MIN_VALUE;
        for (int i = 0; i < C; i++) {
            int index = Integer.parseInt(b.readLine()) - 1;
            stalls[index] = true;
            lowest = Math.min(index, lowest);
            highest = Math.max(index, highest);
        }
        
        int[] holes = new int[highest - lowest + 1];
        
        int count = 0;
        for (int i = lowest ; i <= highest; i++) {
            if (stalls[i]) {
                holes[count] += 1;
                count = 0;
            } else {
                count++;
            }
        }

        
        int removed = 0;
        
        int index = holes.length - 1;
        for (int i = 1; i < M; i++) {
            while (index > -1 && holes[index] == 0) index--;
            
            if (index <= 0) break;
            
            removed += index;
            holes[index]--;
        }
        
        
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        p.println(highest - lowest + 1 - removed);
        p.close();
    }
}
