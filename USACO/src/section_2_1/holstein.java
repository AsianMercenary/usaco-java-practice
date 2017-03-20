package section_2_1;


import java.io.*;
import static java.lang.Integer.*;
import java.util.*;
/*
ID: william164
LANG: JAVA
TASK: holstein
 */

/**

 @author William
 */
public class holstein {
    static int V; // # types of vitamins
    static int[] req; //size [V]
    static int G; // # types of feeds avaliable
    static int[][] feeds; //size [G][V]
    static ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
    static ArrayList<Integer> output;
    
    public static void main(String[] args) throws IOException {
        BufferedReader R = new BufferedReader(new FileReader("holstein.in"));
        V = parseInt(R.readLine());
        StringTokenizer T = new StringTokenizer(R.readLine());
        req = new int[V];
        
        for (int i = 0; i < V; i++) {
            req[i] = parseInt(T.nextToken());
        }
        
        G = parseInt(R.readLine());
        feeds = new int[G][V];
        
        for (int i = 0; i < G; i++) {
            T = new StringTokenizer(R.readLine());
            for (int j = 0; j < V; j++) {
                feeds[i][j] = parseInt(T.nextToken());
            }
        }
        
        for (int i = 0; i < feeds.length; i++) {
            ArrayList<Integer> q = new ArrayList<>();
            q.add(i);
            queue.add(q);
        }
        
        boolean found = false;
        while (queue.size() > 0 && !found) {
            found = BFS(queue.get(0));
            queue.remove(0);
        }
        
        PrintWriter p = new PrintWriter(new FileWriter("holstein.out"));
        
        p.print(output.size());
        //System.out.print(output.size());
        for (int i : output) {
            //System.out.print(" " + (i + 1));
            p.print(" " + (i + 1));
        }
        p.println();
        p.close();
    }
    
    public static boolean BFS(ArrayList<Integer> q) {
        int[] diet = new int[V];
        
        for (int f : q) {
            int[] feed = feeds[f];
            for (int i = 0; i < V; i++) {
                diet[i] += feed[i];
            }
        }
        
        boolean metDiet = true;
        for (int i = 0; i < V; i++) {
            if (diet[i] < req[i]) {
                metDiet = false;
                break;
            }
        }
        
        if (metDiet) {
            output = q;
            return true;
        }
        
        for (int i = q.get(q.size() - 1) + 1; i < feeds.length; i++) {
            ArrayList<Integer> newqueue = (ArrayList<Integer>) q.clone();
            newqueue.add(i);
            queue.add(newqueue);
        }
        
        return false;
    }
}
