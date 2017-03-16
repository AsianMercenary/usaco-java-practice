package section_1_4;

/*
ID: william164
LANG: JAVA
TASK: milk3
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author William
 */
public class milk3 {

    static boolean[][][] permutations;
    static int[] capacities;
    static ArrayList<Integer> output = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader R = new BufferedReader(new FileReader("milk3.in"));
        StringTokenizer T = new StringTokenizer(R.readLine());
        capacities = new int[]{parseInt(T.nextToken()), parseInt(T.nextToken()), parseInt(T.nextToken())};
        permutations = new boolean[capacities[0] + 1][capacities[1] + 1][capacities[2] + 1];
        
        search(new int[]{0, 0, capacities[2]});
        
        Collections.sort(output);
        PrintWriter p = new PrintWriter(new FileWriter("milk3.out"));
        
        String line = "";
        for (int i : output) {
            line += i + " ";
        }
        line = line.trim();
        //System.out.println(line);
        p.println(line);
        p.close();
    }

    public static void search(int[] permutation) {
        int a = permutation[0];
        int b = permutation[1];
        int c = permutation[2];

        if (permutations[a][b][c]) {
            return;
        }
        if (a == 0) {
            output.add(c);
        }

        permutations[a][b][c] = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    continue;
                }
                poor(permutation, i, j);
                search(permutation);
                permutation[0] = a;
                permutation[1] = b;
                permutation[2] = c;
            }
        }

    }

    public static void poor(int[] permutation, int index1, int index2) {
        int difference = capacities[index2] - permutation[index2];
        if (difference >= permutation[index1]) {
            permutation[index2] += permutation[index1];
            permutation[index1] = 0;
        } else {
            permutation[index2] += difference;
            permutation[index1] -= difference;
        }
    }
}
