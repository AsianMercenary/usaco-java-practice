package chapter_1.section_5;

/*
ID: william164
LANG: JAVA
TASK: pprime
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.*;
/**
 *
 * @author William
 */
public class pprime {
    static int a, b;
    static ArrayList<Integer> palinprimes = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        StringTokenizer T = new StringTokenizer(
                new BufferedReader(new FileReader("pprime.in")).readLine());
        String a = T.nextToken();
        String b = T.nextToken();
        pprime.a = parseInt(a);
        pprime.b = parseInt(b);
        
        for (int i = a.length(); i <= b.length(); i++) {
            testPalindromes(0, (int) Math.pow(10, i-1), 1);
        }
        
        String output = "";
        for (int i : palinprimes) {
            output += i + "\n";
        }
        output = output.trim();
        PrintWriter p = new PrintWriter(new FileWriter("pprime.out"));
        p.println(output);
        System.out.println(output);
        p.close();
    }
    
    public static void testPalindromes(int number, int place1, int place2) {
        if (place1 < place2) {
            if (number < a || number > b) {
                return;
            }
            if (isPrime(number)) {
                palinprimes.add(number);
            }
            return;
        }
        if (place1 == place2) {
            for (int i = 0; i <= 9; i++) {
                int newNumber = number + i * place1;
                testPalindromes(newNumber, place1 / 10, place2 * 10);
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            int newNumber = number + i * place1 + i * place2;
            testPalindromes(newNumber, place1 / 10, place2 * 10);
        }
    }
    
    static boolean isPrime(int number) {
        int root = (int) Math.sqrt(number);
        for (int i = 2; i <= root; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
