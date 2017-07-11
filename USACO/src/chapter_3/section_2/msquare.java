package chapter_3.section_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 ID: william164
 LANG: JAVA
 TASK: msquare
 */
/**
 *
 * @author William
 */
public class msquare {

    static String fin = "";
    static ArrayList<Q> queue = new ArrayList<>();
    static ArrayList<Q> newqueue = new ArrayList<>();
    static ArrayList<String> out = new ArrayList<>();
    static boolean found = false;
    static HashMap<String, Boolean> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("msquare.in"));
        StringTokenizer t = new StringTokenizer(r.readLine());
        for (int i = 0; i < 4; i++) {
            fin += t.nextToken();
        }

        String end = "";
        for (int i = 0; i < 4; i++) {
            end = t.nextToken() + end;
        }
        fin += end;

        Q q = new Q();
        q.m = "";
        q.seq = "12348765";
        newqueue.add(q);
        while (!found) {
            queue = newqueue;
            newqueue = new ArrayList<>();
            while (queue.size() > 0) {
                enqueue(queue.remove(0));
            }
        }

        String min = out.get(0);
        for (String s : out) {
            if (min.compareTo(s) > 0) {
                min = s;
            }
        }
        PrintWriter p = new PrintWriter(new FileWriter("msquare.out"));
        p.println(min.length());
        p.println(min);
        p.close();
    }

    static void enqueue(Q q) {
        if (visited.containsKey(q.seq)) {
            return;
        }
        visited.put(q.seq, true);

        if (q.seq.equals(fin)) {
            found = true;
            out.add(q.m);
            return;
        }

        if (q.a < 1) {
            Q a = new Q();
            a.m = q.m + "A";
            a.a = q.a + 1;
            a.seq = A(q.seq);
            newqueue.add(a);
        }

        if (q.b < 3) {
            Q b = new Q();
            b.m = q.m + "B";
            b.b = q.b + 1;
            b.seq = B(q.seq);
            newqueue.add(b);
        }

        if (q.c < 3) {
            Q c = new Q();
            c.m = q.m + "C";
            c.c = q.c + 1;
            c.seq = C(q.seq);
            newqueue.add(c);
        }
    }

    static String A(String s) {
        String a = s.substring(0, 4);
        String b = s.substring(4, 8);
        return b + a;
    }

    static String B(String s) {
        String a = s.substring(3, 4) + s.substring(0, 3);
        String b = s.substring(7, 8) + s.substring(4, 7);
        return a + b;
    }

    static String C(String s) {
        return s.substring(0, 1) + s.substring(5, 6) + s.substring(1, 2)
                + s.substring(3, 5) + s.substring(6, 7) + s.substring(2, 3)
                + s.substring(7, 8);
    }
}

class Q {

    String seq;
    String m;
    int a = 0, b = 0, c = 0;
}
