package chapter_1.section_2;


import java.io.*;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: dualpal
 */

/**
 *
 * @author William
 */
public class dualpal {
    public static void main(String[] args) throws IOException {
        StringTokenizer s = new StringTokenizer(new BufferedReader(new FileReader("dualpal.in")).readLine());
        int N = Integer.parseInt(s.nextToken());
        int S = Integer.parseInt(s.nextToken());
        
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        
        int count = 0;
        int i = S+1;
        while (count < N) {
            int palinCount = 0;
            for (int j = 2; j <= 10; j++) {
                String t = convert(i, j);
                if (isPalin(t)) {
                    
                    palinCount++;
                }
                System.out.println(i + " " + j + " " + t);
                if (palinCount == 2) {
                    p.println(i);
                    count++;
                    break;
                }
            }
            i++;
        }
        p.close();
    }
    
    public static String convert(int number, int base) {
        int max = 1;
        while (max <= number) {max *= base;}
        max /= base;
        String converted = "";
        while (max > 0) {
            converted += number / max;
            number = number % max; 
            max /= base;
        }
        return converted;
    }
    
    public static boolean isPalin(String s) {
        if (s.length() <= 1) {
            return true;
        }
        
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalin(s.substring(1, s.length() - 1));
    }
}
