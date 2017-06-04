package chapter_2.section_3;

/*
 ID: william164
 LANG: JAVA
 TASK: concom
 */

import java.util.*;
import java.io.*;

/*

 @author William
 */
public class concom {

    static Company[] companies;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("concom.in"));
        int lines = Integer.parseInt(b.readLine());

        companies = new Company[101];
        
        for (int line = 0; line < lines; line++) {
            StringTokenizer t = new StringTokenizer(b.readLine());
            int i = Integer.parseInt(t.nextToken());
            int j = Integer.parseInt(t.nextToken());
            int p = Integer.parseInt(t.nextToken());

            if (i == j) {
                continue;
            }
            
            if (companies[i] == null) {
                companies[i] = new Company();
            }
            
            if (companies[j] == null) {
                companies[j] = new Company();
            }

            companies[i].shares[j] += p;
            companies[j].sales[i] += p;

            if (companies[i].shares[j] > 50) {
                int[] shares = companies[j].shares;
                for (int s = 0; s < shares.length; s++) {
                    companies[i].shares[s] += shares[s];
                    if (companies[s] == null) {
                        companies[s] = new Company();
                    }
                    companies[s].sales[i] += shares[s];
                    int[] sales = companies[i].sales;
                    for (int owner = 0; owner < sales.length; owner++) {
                        if (sales[owner] > 50) {
                            companies[owner].shares[s] += shares[s];
                            companies[s].sales[owner] += shares[s];
                        }
                    }
                }
            }
            
            visited = new boolean[101];
            fixup(i, j, p);
        }

        PrintWriter p = new PrintWriter(new FileWriter("concom.out"));
        for (int i = 0; i < companies.length; i++) {
            if (companies[i] == null) {
                continue;
            }
            int[] shares = companies[i].shares;
            for (int j = 0; j < shares.length; j++) {
                if (shares[j] > 50 && i != j) {
                    //System.out.println(i + " " + j);
                    p.println(i + " " + j);
                }
            }
        }
        p.close();
    }
    
    static boolean[] visited = new boolean[101];
    
    public static void fixup(int i, int j, int p) {
        if (visited[i]) {
            return;
        }
        
        visited[i] = true;
        
        int[] sales = companies[i].sales;
        for (int s = 0; s < sales.length; s++) {
            if (sales[s] > 50) {
                companies[s].shares[j] += p;
                companies[j].sales[s] += p;
                fixup(s, j, p);
            }
        }
    }
}

class Company {

    int[] shares;
    int[] sales;

    Company() {
        shares = new int[101];
        sales = new int[101];
    }
}
