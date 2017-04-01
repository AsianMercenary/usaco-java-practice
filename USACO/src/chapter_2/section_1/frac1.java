package chapter_2.section_1;

import java.io.*;
import java.util.*;
/*
ID: william164
LANG: JAVA
TASK: frac1
 */

/**

 @author William
 */
public class frac1 {
    static boolean[][] seenFract;
    static ArrayList<fraction> fractions = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new FileReader("frac1.in")).readLine());
        seenFract = new boolean[N + 1][N + 1];
        
        for (int denom = 1; denom <= N; denom++) {
            for (int numer = denom; numer >= 0; numer--) {
                if (seenFract[denom][numer]) {
                    continue;
                }
                
                fractions.add(new fraction(numer + "/" + denom, (double) numer / denom));
                
                for (int i = 2; i * denom <= N; i++) {
                    seenFract[i * denom][i * numer] = true;
                }
            }
        }
        
        Collections.sort(fractions);
        
        PrintWriter p = new PrintWriter(new FileWriter("frac1.out"));
        for (fraction f : fractions) {
            System.out.println(f);
            p.println(f);
        }
        p.close();
    }
}

class fraction implements Comparable<fraction> {
    String asString;
    double value;
    
    fraction(String asString, double value) {
        this.asString = asString;
        this.value = value;
    }

    @Override
    public int compareTo(fraction o) {
        if (value > o.value) return 1;
        if (value < o.value) return -1;
        return 0;
    }
    
    @Override
    public String toString() {
        return asString;
    }
}
