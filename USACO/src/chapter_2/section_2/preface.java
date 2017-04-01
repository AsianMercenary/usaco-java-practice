package chapter_2.section_2;


import java.io.*;
import java.util.*;

/*
ID: william164 
LANG: JAVA 
TASK: preface
 */
/**

 @author William
 */
public class preface {

    static Numeral[] count = {new Numeral("M", 1000), new Numeral("CM", 900), new Numeral("D", 500), new Numeral("CD", 400), new Numeral("C", 100), new Numeral("XC", 90), new Numeral("L", 50), new Numeral("XL", 40), new Numeral("L", 50), new Numeral("XL", 40), new Numeral("X", 10), new Numeral("IX", 9), new Numeral("V", 5), new Numeral("IV", 4), new Numeral("I", 1)};
    static char[] letters = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    static int[] lettercount = {0, 0, 0, 0, 0, 0, 0};
    
    public static void main(String[] args) throws IOException {
        int i = Integer.parseInt(new BufferedReader(new FileReader("preface.in")).readLine());
        for (int a = 1; a <= i; a++) {
            roman(a);
        }
        PrintWriter p = new PrintWriter(new FileWriter("preface.out"));
        for (int a = 0; a < lettercount.length; a++) {
            if (lettercount[a] != 0) {
                for (int b = lettercount.length - 1; b >= a; b--) {
                    p.println(letters[b] + " " + lettercount[b]);
                    //System.out.println(letters[b] + " " + lettercount[b]);
                }
                break;
            }
        }
        p.close();
    }
    
    public static void roman(int i) {
        int index = 0;
        for (Numeral n : count) {
            if (i == 0) {
                break;
            }
            int value = n.value;
            String letters = n.letters;
            if (index % 2 == 0) {
                for (int a = 0; a < 3; a++) {
                    if (i - value >= 0) {
                        deconstruct(letters, value);
                        i -= value;
                    }
                }
            } else if (i - value >= 0) {
                deconstruct(letters, value);
                i -= value;
            }
            index++;
        }
    }

    public static void deconstruct(String letters, int value) {
        for (char c : letters.toCharArray()) {
            switch(c) {
                case 'I':
                    lettercount[6]++;
                    break;
                case 'V':
                    lettercount[5]++;
                    break;
                case 'X':
                    lettercount[4]++;
                    break;
                case 'L':
                    lettercount[3]++;
                    break;
                case 'C':
                    lettercount[2]++;
                    break;
                case 'D':
                    lettercount[1]++;
                    break;
                case 'M':
                    lettercount[0]++;
                    break;
            }
        }
    }
}

class Numeral {

    int value;
    String letters;

    Numeral(String letters, int value) {
        this.letters = letters;
        this.value = value;
    }
}
