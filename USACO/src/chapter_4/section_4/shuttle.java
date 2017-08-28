package chapter_4.section_4;

/*
 ID: william164
 LANG: JAVA
 TASK: shuttle
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**

 @author William
 */
public class shuttle {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("shuttle.in"));
        N = Integer.parseInt(r.readLine());
        char[] c = new char[2 * N + 1];
        for (int i = 0; i < N; i++) {
            c[i] = 'W';
            c[c.length - 1 - i] = 'B'; 
        }
        c[N] = ' ';
        
        moves = 1;
        boolean stop = false;
        while (!stop) {
            stop = search(c, N, 1);
            moves++;
        }
        PrintWriter w = new PrintWriter(new FileWriter("shuttle.out"));
        int move = 0;
        while (output != null) {
            if (move == 20) {
                move = 0;
                w.println();
            }
            if (move != 0) {
                w.print(" ");
            }
            w.print(output.move);
            output = output.next;
            move++;
        }
        w.println();
        w.close();
    }
    static Node output = null;
    static int moves;
    public static boolean search(char[] c, int hole, int move) {
        if (move > moves) {
            for (int i = 0; i < N; i++) {
                if (c[i] != 'B') {
                    return false;
                }
            }
            
            for (int i = N + 1; i < c.length; i++) {
                if (c[i] != 'W') {
                    return false;
                }
            }
            return true;
        }
        
        if (hole - 2 >= 0 && c[hole - 2] == 'W') {
            c[hole] = c[hole - 2];
            c[hole - 2] = ' ';
            boolean found = search(c, hole - 2, move + 1);
            if (found) {
                Node next = new Node();
                next.next = output;
                next.move = hole - 1;
                output = next;
                return true;
            }
            c[hole - 2] = c[hole];
            c[hole] = ' ';
        }
        
        if (hole - 1 >= 0 && c[hole - 1] == 'W') {
            c[hole] = c[hole - 1];
            c[hole - 1] = ' ';
            boolean found = search(c, hole - 1, move + 1);
            if (found) {
                Node next = new Node();
                next.next = output;
                next.move = hole;
                output = next;
                return true;
            }
            c[hole - 1] = c[hole];
            c[hole] = ' ';
        }
        
        if (hole + 1 < c.length && c[hole + 1] == 'B') {
            c[hole] = c[hole + 1];
            c[hole + 1] = ' ';
            boolean found = search(c, hole + 1, move + 1);
            if (found) {
                Node next = new Node();
                next.next = output;
                next.move = hole + 2;
                output = next;
                return true;
            }
            c[hole + 1] = c[hole];
            c[hole] = ' ';
        }
        
        if (hole + 2 < c.length && c[hole + 2] == 'B') {
            c[hole] = c[hole + 2];
            c[hole + 2] = ' ';
            boolean found = search(c, hole + 2, move + 1);
            if (found) {
                Node next = new Node();
                next.next = output;
                next.move = hole + 3;
                output = next;
                return true;
            }
            c[hole + 2] = c[hole];
            c[hole] = ' ';
        }
        return false;
    }
    static class Node {
        Node next = null;
        int move;
    }
}
