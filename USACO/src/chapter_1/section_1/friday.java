package chapter_1.section_1;


import java.io.*;
import java.util.*;

/*
ID: william164
LANG: JAVA
TASK: friday
 */
/**
 *
 * @author William
 */
public class friday {

    public static void main(String args[]) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("friday.in"));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        //jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec
        int[] MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int N = Integer.parseInt(b.readLine());
        int unlucky[] = new int[7];
        int offset = 2;
        for (int i = 0; i < N; i++) {
            if (((i + 1900) % 4 == 0 && (i + 1900) % 100 != 0) || (i + 1900) % 400 == 0) {
                MONTH[1] = 29;
            } else {
                MONTH[1] = 28;
            }
            for (int j = 0; j < MONTH.length; j++) {
                int day = (13 + offset - 1) % 7;
                unlucky[day]++;
                offset = (offset + MONTH[j]) % 7;
            }
            
        }
        String output = Arrays.toString(unlucky);
        p.println(output.substring(1, output.length() - 1).replace(",", ""));
        p.close();
    }
}
