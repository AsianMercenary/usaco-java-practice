package chapter_2.section_2;

import java.io.*;
import java.util.*;
/*
 ID: william164
 LANG: JAVA
 TASK: runround
 */

/**

 @author William
 */
public class runround {
    public static void main(String[] args) throws IOException {
        int M = Integer.parseInt(new BufferedReader(new FileReader("runround.in")).readLine());
        
        for (int i = M+1; i > 0; i++) {
            if (isRunRound(i)) {
                PrintWriter p = new PrintWriter(new FileWriter("runround.out"));
                p.println(i);
                System.out.println(i);
                p.close();
                break;
            }
        }
    }
    
    public static boolean isRunRound(int N) {
        List<Integer> digits = new ArrayList<>();
        boolean[] checked = new boolean[10];
        
        do {
            int digit = N % 10;
            if (digit == 0 || checked[digit]) {
                return false;
            }
            
            checked[digit] = true;
            digits.add(0, digit);
            N /= 10;
        } while (N > 0);
        
        checked = new boolean[digits.size()];
        int startIndex = 0;
        for (int i = 0; i < digits.size(); i++) {
            startIndex = (startIndex + digits.get(startIndex)) % digits.size();
            if (checked[startIndex]) {
                return false;
            }
            checked[startIndex] = true;
        }
        
        return startIndex == 0;
    }

}
