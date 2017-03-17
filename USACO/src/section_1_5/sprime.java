package section_1_5;

/*
ID: william164
LANG: JAVA
TASK: sprime
 */
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author William
 */
public class sprime {

    static int length;
    static ArrayList<Integer> sprimes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("sprime.in"));
        length = Integer.parseInt(b.readLine());
        search(0, 1);
        PrintWriter p = new PrintWriter(new FileWriter("sprime.out"));
        for (int i : sprimes) {
            p.println(i);
            System.out.println(i);
        }
        p.close();
    }
    
    static void search(int number, int place) {
        if (place > length) {
            sprimes.add(number);
            return;
        }
        number *= 10;
        for (int i = 1; i <= 9; i++) {
            int newnumber = number + i;
            if (isPrime(newnumber)){
                search(newnumber, place + 1);
            }
        }
    }

    static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
