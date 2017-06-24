package chapter_2.section_4;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: fracdec
 */
/**

 @author William
 */
public class fracdec {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("fracdec.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(t.nextToken());
        int D = Integer.parseInt(t.nextToken());

        int wholenum = N / D;
        N %= D;
        N *= 10;

        int[] numerators = new int[1000000];
        for (int i = 0; i < numerators.length; i++) {
            numerators[i] = -1;
        }

        Node first = new Node();
        first.value = wholenum;
        Node current = first;

        int repeat = -1;
        int index = 1;
        while (true) {
            if (N == 0) {
                break;
            }

            if (numerators[N] != -1) {
                repeat = numerators[N];
                break;
            }

            numerators[N] = index;
            current.next = new Node();
            current = current.next;
            current.value = N / D;
            N %= D;
            N *= 10;

            index++;
        }

        ArrayList<String> output = new ArrayList<>();
        String line = "";
        if (repeat == 0) {
            line += "(";
        }
        line += first.value;
        line += ".";
        if (line.length() > 76) {
            output.add(line.substring(0, 76));
            line = line.substring(76);
        }
        first = first.next;
        int i = 1;
        while (first != null) {
            if (repeat == i) {
                line += "(";
            }
            line += first.value;
            if (line.length() > 76) {
                output.add(line.substring(0, 76));
                line = line.substring(76);
            }
            first = first.next;
            i++;
        }
        
        if (index == 1) {
            line += 0;
        }

        if (repeat != -1) {
            line += ")";
        }

        if (line.length() > 76) {
            output.add(line.substring(0, 76));
            line = line.substring(76);
        }
        
        if (line.length() > 0) {
            output.add(line);
        }
        
        PrintWriter p = new PrintWriter(new FileWriter("fracdec.out"));
        for (String s : output) {
            p.println(s);
            System.out.println(s);
        }
        p.close();
    }
}

class Node {

    int value;
    Node next = null;
}
