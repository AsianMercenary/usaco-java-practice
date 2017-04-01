package chapter_2.section_2;


import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

/*
 ID: william164
 LANG: JAVA
 TASK: lamps
 */
/**

 @author William
 */
public class lamps {

    static int N;
    static int fC;
    static int c;

    static boolean[] ons;
    static boolean[] offs;
    static int max;

    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<Integer> old = new ArrayList<>();
    static ArrayList<Integer> New = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader R = new BufferedReader(new FileReader("lamps.in"));
        N = parseInt(R.readLine().trim());
        fC = parseInt(R.readLine().trim());

        StringTokenizer T = new StringTokenizer(R.readLine());

        ons = new boolean[N + 1];
        offs = new boolean[N + 1];

        int currentInt;
        while ((currentInt = parseInt(T.nextToken())) != -1) {
            ons[currentInt] = true;
        }

        //System.out.println(ons.toString(2));
        T = new StringTokenizer(R.readLine());
        while ((currentInt = parseInt(T.nextToken())) != -1) {
            offs[currentInt] = true;
        }

        //System.out.println(offs.toString(2));
        old.add(0b1);
        while (c < fC) {
            for (int l : old) {
                countChanges(l);
            }
            c++;
            old = New;
            New = new ArrayList<>();
        }

        for (int i : old) {
            boolean allOn = (i & 0b1) == 0b1;
            boolean oddsOn = (i & 0b10) == 0b10;
            boolean evensOn = (i & 0b100) == 0b100;
            boolean thirdsOn = (i & 0b1000) == 0b1000;

            boolean isValid = true;

            String lights = "";
            for (int j = 1; j <= N; j++) {
                boolean state = false;
                if (allOn) {
                    state = true;
                }
                if (j % 2 == 0) {
                    if (evensOn) {
                        state = !state;
                    }
                } else if (oddsOn) {
                    state = !state;
                }
                if (j % 3 == 1) {
                    if (thirdsOn) {
                        state = !state;
                    }
                }

                if (state) {
                    if (offs[j]) {
                        isValid = false;
                        break;
                    }
                } else if (ons[j]) {
                    isValid = false;
                    break;
                }

                lights = lights + ((state) ? 1 : 0);
            }

            if (isValid) {

                list.add(lights);

                //System.out.println(allOn + " " + oddsOn + " " + evensOn + " " + thirdsOn);
                //System.out.println(lights);
            }
        }

        PrintWriter p = new PrintWriter(new FileWriter("lamps.out"));
        Collections.sort(list);
        if (list.isEmpty()) {
            p.println("IMPOSSIBLE");
            p.close();
            System.exit(0);
        }
        for (String o : list) {
            while (o.length() < N) {
                o = "0";
            }
            p.println(o);
            //System.out.println(o);
        }
        p.close();
    }

    public static void countChanges(int switches) {

        int newswitch = switches;
        newswitch ^= 0b1;
        add(newswitch);

        newswitch = switches;
        newswitch ^= 0b10;
        add(newswitch);

        newswitch = switches;
        newswitch ^= 0b100;
        add(newswitch);

        newswitch = switches;
        newswitch ^= 0b1000;
        add(newswitch);
    }

    public static void add(int i) {
        if (!New.contains(i)) {
            New.add(i);
        }
    }

}
