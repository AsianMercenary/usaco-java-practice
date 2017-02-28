package section_1_1;


import java.io.*;
import java.util.Arrays;

/*
ID: william164
LANG: JAVA
TASK: beads
*/
/**
 *
 * @author William
 */
public class beads {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("beads.in"));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int N = Integer.parseInt(b.readLine());
        String necklace = b.readLine();

        String[] beads = (necklace + necklace).split("");
        int max = 0;

        int before = 0;
        int index = 0;
        while (index < beads.length / 2) {
            int now = 0;
            int nextIndex = index;
            boolean found = false;
            String compare = beads[index];
            for (int i = index; i < beads.length; i++) {
                if (compare.equals("w")) {
                    compare = beads[i];
                }

                if (beads[i].equals("w")) {
                    if (!found) {
                        nextIndex = i;
                        found = true;
                    }
                } else if (beads[i].equals(compare)) {
                    found = false;
                } else {
                    if (!found) {
                        nextIndex = i;
                        found = true;
                    }
                    break;
                }
                now++;
            }
            if (nextIndex == index) {
                max = beads.length / 2;
                break;
            }
            //System.out.println("Now: " + now + " Before: " + before + " Next: " + nextIndex + " Index: " + index);
            max = Math.max(max, before + now);
            before = nextIndex - index;
            index = nextIndex;
        }

        p.println(max < beads.length / 2 ? max : beads.length / 2);
        p.close();
    }
}