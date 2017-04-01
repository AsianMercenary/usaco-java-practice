package chapter_1.section_1;


import java.io.*;
import java.util.*;

/*
ID: william164
LANG: JAVA
TASK: gift1
 */
/**
 *
 * @author William
 */
public class gift1 {

    static final String subname = "gift1";

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(subname + ".in"));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(subname + ".out")));
        StringTokenizer st;

        int n = Integer.parseInt(b.readLine());
        LinkedHashMap<String, Integer> h = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            h.put(b.readLine(), 0);
        }

        String name;
        while ((name = b.readLine()) != null) {
            st = new StringTokenizer(b.readLine());
            int money = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            if (people > 0) {
                if (money != 0) {
                    int split = money / people;
                    int remainder = money % (split * people);
                    for (int i = 0; i < people; i++) {
                        String recipient = b.readLine();
                        h.put(recipient, h.get(recipient) + split);

                    }
                    h.put(name, h.get(name) + remainder - money);
                } else {
                    for (int i = 0; i < people; i++) {
                        b.readLine();
                    }
                }
            }

        }
        for (String N : h.keySet()) {
            p.println(N + " " + h.get(N));
        }
        p.close();
    }
}
