package chapter_3.section_4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/*
ID: william164
LANG: JAVA
TASK: heritage
 */

/**
 *
 * @author William
 */
public class heritage {
    static HashMap<Character, Node> nodes = new HashMap<>();
    static char[] inOrder;
    static char[] preOrder;
    static int run = 0;
    static String output = "";
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("heritage.in"));
        inOrder = r.readLine().toCharArray();
        preOrder = r.readLine().toCharArray();
        char start = preOrder[0];
        search(0, inOrder.length - 1);
        traverse(start);
        PrintWriter p = new PrintWriter(new FileWriter("heritage.out"));
        p.println(output);
        p.close();
    }
    
    public static void traverse(char node) {
        if (node == 0) {
            return;
        }
        
        Node n = nodes.get(node);
        traverse(n.left);
        traverse(n.right);
        output += node;
    }
    
    public static char search(int low, int high) {
        if (high < low) {
            return 0;
        }
        
        int pivot = -1;
        for (int i = low; i <= high; i++) {
            if (inOrder[i] == preOrder[run]) {
                pivot = i;
                run++;
                break;
            }
        }
        char left = search(low, pivot - 1);
        char right = search(pivot + 1, high);
        Node n = new Node();
        n.left = left;
        n.right = right;
        nodes.put(inOrder[pivot], n);
        return inOrder[pivot];
    }
}

class Node {
    char left, right;
}
