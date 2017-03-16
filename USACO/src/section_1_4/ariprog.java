package section_1_4;

/*
ID: william164
LANG: JAVA
TASK: ariprog
 */

import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author William
 */
public class ariprog {

    static int size = 1;
    static ArrayList<Integer> bisquares = new ArrayList<>();
    static boolean[] biDict;
    static int N;
    static int count;
    static ArrayList<ArrayList<Integer>> output;

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis() / 1000;

        BufferedReader R = new BufferedReader(new FileReader("ariprog.in"));
        N = Integer.parseInt(R.readLine());
        int M = Integer.parseInt(R.readLine());

        biDict = new boolean[2 * (int) Math.pow(M, 2) + 1];

        for (int p = 0; p <= M; p++) {
            for (int q = p; q <= M; q++) {
                int bisquare = (int) (Math.pow(p, 2) + Math.pow(q, 2));
                if (!biDict[bisquare]) {
                    bisquares.add(bisquare);
                    biDict[bisquare] = true;
                }
            }
        }
        Collections.sort(bisquares);
        
        output = new ArrayList<>();
        output.add(new ArrayList<>());
        for (int i = 1; i <= (biDict.length - 1) / (N - 1); i++) {
            output.add(new ArrayList<>());
            boolean[] temp = biDict.clone();
            for (int bisquare : bisquares) {
                search(bisquare, i, temp);
            }
        }

        PrintWriter p = new PrintWriter(new FileWriter("ariprog.out"));
        if (count > 0) {
            for (int i = 1; i < output.size(); i++) {
                ArrayList<Integer> a = output.get(i);
                Collections.sort(a);
                for (int j : a) {
                    p.println(j + " " + i);
                }
            }
        } else {
            p.println("NONE");
        }
        p.close();
    }
    
    public static int search(int a, int b, boolean[] temp) {
        if (a > temp.length - 1 || !temp[a]) {
            return 0;
        }
        
        temp[a] = false;
        
        int value = 1 + search(a + b, b, temp);
        if (value >= N) {
            count++;
            output.get(b).add(a);
        }
        return value;
    }

}
