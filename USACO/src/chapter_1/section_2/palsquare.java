package chapter_1.section_2;


import java.io.*;

/*
ID: william164
LANG: JAVA
TASK: palsquare
 */

/**
 *
 * @author William
 */
public class palsquare {
    public static void main(String[] args) throws IOException{
        int base = Integer.parseInt(new BufferedReader(new FileReader("palsquare.in")).readLine());
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        for (int i = 1; i < 300; i++) {
            int square = (int) Math.pow(i, 2);
            String s = Integer.toString(square,base).toUpperCase();
            if (isPalindrome(s)) {
                p.println(Integer.toString(i, base).toUpperCase() + " " + s);
            }
        }
        p.close();
    }
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        
        return isPalindrome(s.substring(1, s.length() - 1));
    }
}
