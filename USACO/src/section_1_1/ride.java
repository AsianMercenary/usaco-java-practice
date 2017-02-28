package section_1_1;

/*
ID: william164
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
    public static void main(String[] args) throws IOException {
        String fileName = "ride";
        BufferedReader b = new BufferedReader(new FileReader(fileName + ".in"));
        StringTokenizer t = new StringTokenizer(b.readLine() + " " + b.readLine());
        
        int one = 1, two = 1;
        for (char c : t.nextToken().toCharArray()) {
            one *= c - 65 + 1; 
        }
        
        for (char c : t.nextToken().toCharArray()) {
            two *= c - 65 + 1; 
        }
        
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        
        p.println((one % 47 == two % 47) ? "GO" : "STAY");
        p.close();
    }
}
