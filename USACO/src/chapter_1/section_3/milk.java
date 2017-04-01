package chapter_1.section_3;


import java.io.*;
import java.util.*;

/*
ID: william164
LANG: JAVA
TASK: milk
 */
public class milk {

    static farmer[] farmers;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("milk.in"));
        StringTokenizer t;

        t = new StringTokenizer(b.readLine());
        int N = Integer.parseInt(t.nextToken());
        int M = Integer.parseInt(t.nextToken());

        farmers = new farmer[M];

        for (int i = 0; i < M; i++) {
            t = new StringTokenizer(b.readLine());
            farmers[i] = new farmer(Integer.parseInt(t.nextToken()), Integer.parseInt(t.nextToken()));

        }

        sortFarmers(0, M - 1);
        
        int price = 0;
        int amount = 0;
        int index = 0;
        while (amount < N) {
            int need = N - amount;
            if (need < farmers[index].amount) {
                price += need * farmers[index].price;
                amount += need;
            } else {
                price += farmers[index].price * farmers[index].amount;
                amount += farmers[index].amount;
            }
            index++;
        }

        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        p.println(price);
        p.close();
        
    }

    public static void sortFarmers(int low, int high) {
        
        if (high - low < 1) {
            return;
        }

        int partition = farmers[low].price;

        int i = low + 1, j = high;
        while (true) {
            for (i = i; i < high && farmers[i].price <= partition; i++);
            for (j = j; j > low && farmers[j].price >= partition; j--);

            if (i >= j) {
                break;
            }

            farmer temp = farmers[i];
            farmers[i] = farmers[j];
            farmers[j] = temp;
        }

        farmer temp = farmers[low];
        farmers[low] = farmers[j];
        farmers[j] = temp;

        
        sortFarmers(low, j - 1);
        sortFarmers(j + 1, high);
        
    }

}

class farmer {

    int price;
    int amount;

    farmer(int price, int amount) {
        this.price = price;
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return price + " " + amount;
    }
}
