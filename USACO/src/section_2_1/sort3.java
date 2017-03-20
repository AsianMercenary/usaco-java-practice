package section_2_1;

/*
ID: william164
LANG: JAVA
TASK: sort3
 */
import java.io.*;
import java.util.*;

/**

 @author William
 */
public class sort3 {

    static int N;
    static int[] orgMedals;
    static int[] sortedMedals;
    static ArrayList<queue> queues = new ArrayList<>();
    static boolean sorted = false;
    static int cycles = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader R = new BufferedReader(new FileReader("sort3.in"));
        int N = Integer.parseInt(R.readLine().trim());
        orgMedals = new int[N];
        for (int i = 0; i < N; i++) {
            orgMedals[i] = Integer.parseInt(R.readLine().trim());
        }
        sortedMedals = orgMedals.clone();
        Arrays.sort(sortedMedals);

        queues.add(new queue(orgMedals, 0));
        int index = 0;
        while (queues.size() > index && !sorted) {
            swap2(queues.get(index));
            index++;
        }

        PrintWriter p = new PrintWriter(new FileWriter("sort3.out"));
        p.println(cycles);
        System.out.println(cycles);
        p.close();
    }

    static void swap2(queue q) {
        boolean sorted = true;
        int[] medals = q.list;
        ArrayList<queue> newqueues = new ArrayList<>();
        for (int i = 0; i < medals.length; i++) {
            if (medals[i] != sortedMedals[i]) {
                sorted = false;
                for (int j = i + 1; j < medals.length; j++) {
                    if (medals[j] != sortedMedals[j] && (medals[j] == sortedMedals[i] && medals[i] == sortedMedals[j])) {
                        int[] newmedals = medals.clone();
                        int temp = newmedals[i];
                        newmedals[i] = newmedals[j];
                        newmedals[j] = temp;
                        queue newqueue = new queue(newmedals, q.cycles + 1);
                        queues.add(newqueue);
                        return;
                    }
                    if (medals[j] != sortedMedals[j] && (medals[j] == sortedMedals[i] || medals[i] == sortedMedals[j])) {
                        int[] newmedals = medals.clone();
                        int temp = newmedals[i];
                        newmedals[i] = newmedals[j];
                        newmedals[j] = temp;
                        queue newqueue = new queue(newmedals, q.cycles + 1);
                        newqueues.add(newqueue);
                    }
                }
            }
        }
        
        for (queue current : newqueues) {
            queues.add(current);
        }
        sort3.sorted = sorted;
        if (sorted) {
            cycles = q.cycles;
        }
    }
}

class queue {

    int[] list;
    int cycles;

    queue(int[] list, int cycles) {
        this.list = list;
        this.cycles = cycles;
    }

    @Override
    public boolean equals(Object o) {
        queue q = (queue) o;
        for (int i = 0; i < list.length; i++) {
            if (q.list[i] != list[i]) {
                return false;
            }
        }
        return true;
    }
}
