package chapter_5.section_1;

import java.io.*;
import java.util.*;
/*
 ID: william164
 LANG: JAVA
 TASK: fc
 */

/**

 @author William
 */
public class fc {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("fc.in"));
        Point[] points = new Point[Integer.parseInt(r.readLine())];
        for (int i = 0; i < points.length; i++) {
            points[i] = Point.parsePoint(r.readLine());
        }
        Arrays.sort(points);
        Point[] top = new Point[points.length];
        int ti = 2;
        top[0] = points[0];
        top[1] = points[1];
        Point[] bot = new Point[points.length];
        int bi = 2;
        bot[0] = points[points.length - 1];
        bot[1] = points[points.length - 2];
        for (int i = 2; i < points.length; i++) {
            top[ti++] = points[i];
            while (ti > 2 && !makesRight(top[ti - 1], top[ti-2], top[ti-3])) {
                top[ti - 2] = top[ti - 1];
                top[ti - 1] = null;
                ti--;
            }
        }
        for (int i = points.length - 3; i >= 0; i--) {
            bot[bi++] = points[i];
            while (bi > 2 && !makesRight(bot[bi-1], bot[bi-2], bot[bi-3])) {
                bot[bi - 2] = bot[bi - 1];
                bot[bi - 1] = null;
                bi--;
            }
        }
        double output = 0;
        for (int i = 1; i < top.length && top[i] != null; i++) {
            output += top[i - 1].dist(top[i]);
        }
        
        for (int i = 1; i < bot.length && bot[i] != null; i++) {
            output += bot[i - 1].dist(bot[i]);
        }
        PrintWriter w = new PrintWriter(new FileWriter("fc.out"));
        w.println(String.format("%.2f", output));
        //System.out.println(String.format("%.2f", output));
        w.close();
    }
    
    static boolean makesRight(Point a, Point b, Point c) {
        double p1x = b.x - a.x;
        double p1y = b.y - a.y;
        double p2x = c.x - b.x;
        double p2y = c.y - b.y;
        return p1x * p2y - p2x * p1y < 0;
    }
    
    static class Point implements Comparable<Point> {
        double x, y;
        static Point parsePoint(String s) {
            StringTokenizer t = new StringTokenizer(s);
            double x, y;
            x = Double.parseDouble(t.nextToken());
            y = Double.parseDouble(t.nextToken());
            Point output = new Point();
            output.x = x;
            output.y = y;
            return output;
        }
        
        double dist(Point b) {
            return Math.sqrt(Math.pow(x - b.x, 2) + Math.pow(y - b.y, 2));
        }
        
        public int compareTo(Point o) {
            if (x > o.x) {
                return 1;
            } else if (x < o.x) {
                return -1;
            } else {
                if (y > o.y) {
                    return 1;
                } else if (y < o.y) {
                    return -1;
                }
            }
            return 0;
        }

    }
}
