package section_1_3;


import java.io.*;
import java.util.StringTokenizer;

/*
ID: william164
LANG: JAVA
TASK: crypt1
*/

/**
 *
 * @author William
 */
public class crypt1 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("crypt1.in"));
        int N = Integer.parseInt(r.readLine());
        int[] digits = new int[N];
        StringTokenizer s = new StringTokenizer(r.readLine());
        for (int i = 0; i < N; i++) {
            digits[i] = Integer.parseInt(s.nextToken());
        }
        int count = 0;
        for (int i = 0; i < Math.pow(N, 5); i++) {
            int a, b, c, d, e;
            
            int temp = i;
            a = digits[temp % N];
            temp /= N;
            b = digits[temp % N];
            temp /= N;
            c = digits[temp % N];
            temp /= N;
            d = digits[temp % N];
            temp /= N;
            e = digits[temp % N];
            temp /= N;
            
            if (matched(a,b,c,d,e,digits)) {
                count++;
            }
        }
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        p.println(count);
        p.close();
    }
    
    
    /*
    *      a b c
             d e
        --------
           f g h 
         i j k
       -------
         l m n o
    
       h = c * e % 10
       g = (b * e + c * e / 10) % 10
       f = a * e + (b * e + c * e / 10) / 10 test if this is >= 10 if it is skip
       k = c * d % 10
       j = (b * d + c * d / 10) % 10
       i = a * d + (b * d + c * d / 10) / 10 test if this is >= 10 if it is skip
       o = h
       n = (g + k) % 10
       m = (f + j + (g + k) / 10) % 10
       l = i + (f + j + (g + k) / 10) / 10 test if this is >= 10 if it is skip
     */

    public static boolean matched(int a, int b, int c, int d, int e, int[] digits) {
        int h = c * e % 10;
        int g = (b * e + c * e / 10) % 10;
        int f = a * e + (b * e + c * e / 10) / 10;
        int k = c * d % 10;
        int j = (b * d + c * d / 10) % 10;
        int i =  a * d + (b * d + c * d / 10) / 10;
        int o = h;
        int n = (g + k) % 10;
        int m = (f + j + (g + k) / 10) % 10;
        int l = i + (f + j + (g + k) / 10) / 10;
        
        /*System.out.println("  " + a + " " + b + " " + c);
        System.out.println("x   "         + d + " " + e);
        System.out.println("--------------------");
        System.out.println("  " + f + " " + g + " " + h);
        System.out.println(i + " " + j + " " + k);
        System.out.println(l + " " + m + " " + n + " " + o);
        */
        
        if (!inArray(h, digits)) {
            return false;
        }
        
        if (!inArray(g, digits)) {
            return false;
        }
        
        if (!inArray(f, digits) || f >= 10) {
            return false;
        }
        
        if (!inArray(k, digits)) {
            return false;
        }
        
        if (!inArray(j, digits)) {
            return false;
        }
        
        if (!inArray(i, digits) || i >= 10) {
            return false;
        }
        
        if (!inArray(n, digits)) {
            return false;
        }
        
        if (!inArray(m, digits)) {
            return false;
        }
        
        return !(!inArray(l, digits) || l >= 10);
    }

    public static boolean inArray(int i, int[] digits) {
        for (int a : digits) {
            if (a == i) {
                return true;
            }
        }
        return false;
    }
}
