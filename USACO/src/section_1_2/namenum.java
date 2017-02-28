package section_1_2;


import java.io.*;

/*
ID: william164
LANG: JAVA
TASK: namenum
 */
/**
 *
 * @author William
 */
public class namenum {

    static final char[][] keypad = {{}, {}, 
    {'A', 'B', 'C'},
    {'D', 'E', 'F'},
    {'G', 'H', 'I'},
    {'J', 'K', 'L'},
    {'M', 'N', 'O'},
    {'P', 'R', 'S'},
    {'T', 'U', 'V'},
    {'W', 'X', 'Y'}};

    public static void main(String args[]) throws IOException {
        String s = new BufferedReader(new FileReader("namenum.in")).readLine();

        BufferedReader b = new BufferedReader(new FileReader("dict.txt"));

        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        String previous = "";
        boolean found = false;
        for (int i = 0; i < Math.pow(3, s.length()); i++) {
            String find = "";
            int test = i;
            for (int j = 0; j < s.length(); j++) {
                int index = Integer.parseInt(s.substring(s.length() - 1 - j, s.length() - j));
                find = keypad[index][test % 3] + find;
                test -= test % 3;
                test /= 3;
            }

            
            String line;
            if (previous.compareTo(find) < 0 || previous.length() == 0) {
                while ((line = b.readLine()) != null && line.compareTo(find) < 0) {
                }
                previous = line;
            } else if (previous.compareTo(find) == 0) {
                line = previous;
            } else {
                int skipPoint = -1;
                for (int l = 0; l < find.length(); l++) {
                    if (previous.charAt(l) != find.charAt(l)) {
                        skipPoint = l;
                        break;
                    }
                }
                if (skipPoint == -1) {
                    skipPoint = find.length() - 1;
                }
                
                System.out.println(skipPoint);

                i += (int) Math.pow(3, find.length() - skipPoint - 1) - (int) i % Math.pow(3, find.length() - skipPoint - 1);
                i--;
                continue;
            }
            if (line.compareTo(find) == 0) {
                p.println(find);
                found = true;
            }
            
            System.out.println(find + " " + line);
        }
        if (!found) {
            p.println("NONE");
        }
        p.close();
    }
}
