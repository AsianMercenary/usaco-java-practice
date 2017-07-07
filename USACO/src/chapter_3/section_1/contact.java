package chapter_3.section_1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: contact
 */
/**
 *
 * @author William
 */
public class contact {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("contact.in"));
        StringTokenizer t = new StringTokenizer(b.readLine());
        int A = Integer.parseInt(t.nextToken()),
                B = Integer.parseInt(t.nextToken()),
                N = Integer.parseInt(t.nextToken());
        PrintWriter p = new PrintWriter("contact.out");
        boolean[] msg = new boolean[200000];
        HashMap<Integer, ArrayList<String>> freq = new HashMap<>();
        int size = 0;
        String line;
        while ((line = b.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if (c == '1') {
                    msg[size++] = true;
                } else {
                    msg[size++] = false;
                }
            }
        }
        String prev = "1";
        for (int i = 0; i < A; i++) {
            if (msg[i]) {
                prev += "1";
            } else {
                prev += "0";
            }
        }
        HashMap<String, Integer> count = new HashMap<>();
        for (int i = 0; i + A - 1 < size; i++) {
            if (A > 1) {
                prev = prev.substring(1, A - 1);
                if (msg[i + A - 2]) {
                    prev += "1";
                } else {
                    prev += "0";
                }
            } else {
                prev = "";
            }
            //System.out.println(prev);

            for (int j = i + A - 1; j < i + B && j < size; j++) {
                if (msg[j]) {
                    prev += "1";
                } else {
                    prev += "0";
                }
                //System.out.println(prev);
                if (count.containsKey(prev)) {
                    count.put(prev, count.get(prev) + 1);
                } else {
                    count.put(prev, 1);
                }
            }
        }

        HashMap<Integer, ArrayList<String>> freqs = new HashMap<>();
        Set<String> cs = count.keySet();
        for (String key : cs) {
            int c = count.get(key);
            if (freqs.containsKey(c)) {
                freqs.get(c).add(key);
            } else {
                ArrayList<String> a = new ArrayList<>();
                a.add(key);
                freqs.put(c, a);
            }
        }

        List<Integer> s = new ArrayList<>(freqs.keySet());
        Collections.sort(s);
        for (int i = s.size() - 1; i >= 0 && s.size() - i <= N; i--) {
            p.println(s.get(i));
            //System.out.println(s.get(i));
            String l = "";
            ArrayList<String> f = freqs.get(s.get(i));
            sort(f, 0, f.size() - 1);
            int n = 0;
            for (String c : f) {
                if (n == 6) {
                    p.println(l.trim());
                    l = "";
                    n = 0;
                }
                l += c + " ";
                n++;
            }
            l = l.trim();
            p.println(l);
            //System.out.println(l);
        }
        p.close();
    }

    public static void sort(ArrayList<String> list, int a, int b) {
        if (b <= a) {
            return;
        }
        int mid = (a + b) / 2;
        sort(list, a, mid);
        sort(list, mid + 1, b);
        ArrayList<String> temp = new ArrayList<>();
        for (int i = a, j = mid + 1; true; i = i) {
            if (j <= b && i <= mid) {
                if (compare(list.get(i), list.get(j)) < 1) {
                    temp.add(list.get(i++));
                } else {
                    temp.add(list.get(j++));
                }
            } else if (i <= mid) {
                temp.add(list.get(i++));
            } else if (j <= b) {
                temp.add(list.get(j++));
            } else {
                break;
            }
        }
        for (int i = a; i <= b; i++) {
            list.set(i, temp.get(i - a));
        }
    }

    public static int compare(String a, String b) {
        if (a.length() - b.length() != 0) {
            return a.length() - b.length();
        }
        int ab = Integer.parseInt(a, 2);
        int bb = Integer.parseInt(b, 2);
        return ab - bb;
    }

}
