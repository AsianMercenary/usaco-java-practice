package section_2_1;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: hamming
 */

/**

 @author William
 */
public class hamming {
    static int N; // nunber of codewords
    static int B; // length of codewords
    static int max; // 2^B - 1
    static int D; // hamming distance
    static List<Integer> output = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        StringTokenizer T = new StringTokenizer(new BufferedReader(new FileReader("hamming.in")).readLine());
        N = Integer.parseInt(T.nextToken());
        B = Integer.parseInt(T.nextToken());
        D = Integer.parseInt(T.nextToken());
        max = (int) Math.pow(2, B) - 1;
        
        output.add(0);
        nextInt:
        for (int i = 1; i <= max && output.size() < N; i++) {
            for (int o : output) {
                if (!qualifies(i ^ o)) {
                    continue nextInt;
                }
            }
            output.add(i);
        }
        
        PrintWriter p = new PrintWriter(new FileWriter("hamming.out"));
        
        String out = "";
        for (int i = 0; i <= output.size() + 9; i+=10) {
            String line = "";
            for (int j = i; j < i + 10 && j < output.size(); j++) {
                line += output.get(j) + " ";
            }
            out += line.trim() + "\n";
            
        }
        out = out.trim();
        p.println(out);
        
        p.close();
    }
    
    static boolean qualifies(int i) {
        int count = 0;
        while (count < D && (i & -i) > 0) {
            count++;
            i &= (i - 1);
        }
        return count >= D;
    }
}
