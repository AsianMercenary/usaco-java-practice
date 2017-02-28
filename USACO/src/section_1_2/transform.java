package section_1_2;


import java.io.*;

/*
ID: william164
LANG: JAVA
TASK: transform
 */
/**
 *
 * @author William
 */
public class transform {

    static char[][] pattern;
    static char[][] transformed;
    static char[][] compare;

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("transform.in"));
        int N = Integer.parseInt(b.readLine());
        pattern = new char[N][N];
        transformed = new char[N][N];
        compare = new char[N][N];

        for (int i = 0; i < N; i++) {
            pattern[i] = b.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            compare[i] = b.readLine().toCharArray();
        }

        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        reset();
        
        rotate();
        if (equals()) {
            p.println(1);
            p.close();
            System.exit(0);
        }

        rotate();
        if (equals()) {
            p.println(2);
            p.close();
            System.exit(0);
        }

        rotate();
        if (equals()) {
            p.println(3);
            p.close();
            System.exit(0);
        }

        reset();
        reflectLeft();
        if (equals()) {
            p.println(4);
            p.close();
            System.exit(0);
        }

        reset();
        reflectRight();
        if (equals()) {
            p.println(4);
            p.close();
            System.exit(0);
        }

        reset();
        reflectLeft();

        for (int i = 0; i < 3; i++) {
            rotate();
            if (equals()) {
                p.println(5);
                p.close();
                System.exit(0);
            }
        }

        reset();
        reflectRight();
        for (int i = 0; i < 3; i++) {
            rotate();
            if (equals()) {
                p.println(5);
                p.close();
                System.exit(0);
            }
        }

        reset();
        if (equals()) {
            p.println(6);
            p.close();
            System.exit(0);
        }
        
        p.println(7);
        p.close();
    }

    public static void rotate() {
        char[][] New = new char[transformed.length][transformed.length];

        for (int i = 0; i < transformed.length; i++) {
            char[] a = transformed[i];
            for (int j = 0; j < a.length; j++) {
                New[j][a.length - 1 - i] = a[j];
            }
        }
        transformed = New;
    }

    public static void reflectLeft() {
        char[][] New = new char[transformed.length][transformed.length];

        for (int i = 0; i < transformed.length; i++) {
            char[] a = transformed[i];
            for (int j = 0; j < a.length; j++) {
                New[i][a.length - 1 - j] = a[j];
            }
        }
        transformed = New;
    }

    public static void reflectRight() {
        char[][] New = new char[transformed.length][transformed.length];

        for (int i = 0; i < transformed.length; i++) {
            char[] a = transformed[i];
            for (int j = 0; j < a.length; j++) {
                New[i][j] = a[a.length - 1 - j];
            }
        }
        transformed = New;
    }

    public static boolean equals() {
        for (int i = 0; i < compare.length; i++) {
            for (int j = 0; j < compare[i].length; j++) {
                if (compare[i][j] != transformed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void reset() {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                transformed[i][j] = pattern[i][j];
            }
        }
    }
}
