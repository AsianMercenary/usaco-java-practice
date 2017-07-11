package chapter_3.section_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: william164
 LANG: JAVA
 TASK: fact4
 */

/**
 *
 * @author William
 */
public class fact4 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("fact4.in"));
        int N = Integer.parseInt(r.readLine());
        int[] products = new int[N + 1];
        int two = 0;
        int five = 0;
        for (int i = 2; i <= N; i++) {
            int num = i;
            while (num % 2 == 0) {
                two++;
                num /= 2;
            }
            while (num % 5 == 0){
                five++;
                num /= 5;
            }
            products[i] = num;
        }
        
        int o = 1;
        
        if (two > five) {
            two -= five;
            switch (two % 4) {
                case 1:
                    o = 2;
                    break;
                case 2:
                    o = 4;
                    break;
                case 3:
                    o = 8;
                    break;
                case 0:
                    o = 6;
                    break;
            }
        } else if (two < five) {
            o = 5;
        }
        
        for (int i = 2; i <= N; i++) {
            o *= products[i];
            o %= 10;
        }
        PrintWriter p = new PrintWriter(new FileWriter("fact4.out"));
        p.println(o);
        p.close();
    }
}
