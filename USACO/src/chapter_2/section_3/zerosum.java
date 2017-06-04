package chapter_2.section_3;


import java.io.*;
import java.util.*;

/*
 ID: william164
 LANG: JAVA
 TASK: zerosum
 */
/**

 @author William
 */
public class zerosum {

    static int N;
    static List<List<number>> numbers;
    static ArrayList<Integer> values;
    static int MAX = 98;

    static int ONE = 0b1, TWO = 0b10, THREE = 0b100, FOUR = 0b1000, FIVE = 0b10000, SIX = 0b100000,
            SEVEN = 0b1000000, EIGHT = 0b10000000, NINE = 0b100000000;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new FileReader("zerosum.in")).readLine().trim());
        numbers = new ArrayList<>();
        values = new ArrayList<>();
        for (int i = 1; i <= MAX; i++) {
            ArrayList<number> codes = new ArrayList<>();
            if (i <= N) {
                int shift = 1;
                for (int j = 1; j < i; j++) {
                    shift <<= 1;
                }
                ArrayList<Integer> a = new ArrayList<>();
                a.add(i);
                codes.add(new number(shift, a));
            } else if (isMulti(i)) {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(i);
                codes.add(new number(asCode(i), a));
            }

            int j = 0;
            int k = values.size() - 1;
            while (j < k) {
                int vj = values.get(j);
                int vk = values.get(k);
                int sum = vj + vk;

                if (sum < i) {
                    j++;
                } else if (sum > i) {
                    k--;
                } else {
                    List<number> a = numbers.get(j);
                    List<number> b = numbers.get(k);
                    if (a.size() * b.size() == 0) {
                        continue;
                    }
                    for (number ai : a) {
                        for (number bi : b) {
                            if ((ai.digits & bi.digits) == 0) {
                                int in = ai.digits | bi.digits;
                                ArrayList<Integer> u = new ArrayList<>();
                                for (int x : ai.numbers) {
                                    u.add(x);
                                }
                                for (int x : bi.numbers) {
                                    u.add(x);
                                }
                                sort(u, 0, u.size() - 1);
                                number n = new number(in, u);
                                if (!codes.contains(n)) {
                                    codes.add(n);
                                }
                            }
                        }
                    }
                    j++;
                    k--;
                }
            }

            if (i % 2 == 0) {
                int r = j;
                if (r != -1) {
                    List<number> a = numbers.get(r);
                    for (int c = 0; c < a.size() - 1; c++) {
                        for (int d = c + 1; d < a.size(); d++) {
                            number x = a.get(c);
                            number y = a.get(d);
                            if ((x.digits & y.digits) == 0) {
                                int in = x.digits | y.digits;
                                ArrayList<Integer> u = new ArrayList<>();
                                for (int o : x.numbers) {
                                    u.add(o);
                                }
                                for (int o : y.numbers) {
                                    u.add(o);
                                }
                                sort(u, 0, u.size() - 1);
                                number n = new number(in, u);
                                if (!codes.contains(n)) {
                                    codes.add(n);
                                }
                            }
                        }
                    }
                }

            }

            if (codes.isEmpty()) {
                continue;
            }

            numbers.add(codes);
            values.add(i);

        }
        
        
         /*for (int z = 0; z < values.size(); z++) {

         System.out.println(values.get(z) + ": " + numbers.get(z));
         System.out.println();
         }*/
         

        int match = (int) Math.pow(2, N) - 1;

        ArrayList<String> output = new ArrayList<>();

        for (List<number> n : numbers) {
            if (n.size() < 2) {
                continue;
            }
            for (int i = 0; i < n.size() - 1; i++) {
                for (int j = i + 1; j < n.size(); j++) {
                    number a = n.get(i);
                    number b = n.get(j);
                    if ((a.digits ^ b.digits) == match) {
                        String asExpression;
                        List<Integer> n1 = a.numbers;
                        List<Integer> n2 = b.numbers;

                        boolean n1pos;
                        if (("" + n1.get(0)).charAt(0) == '1') {
                            n1pos = true;
                            asExpression = addSpaces(n1.get(0));
                            n1.remove(0);
                        } else {
                            n1pos = false;
                            asExpression = addSpaces(n2.get(0));
                            n2.remove(0);
                        }

                        while (!n1.isEmpty() || !n2.isEmpty()) {
                            if (!n1.isEmpty() && (n2.isEmpty() || ("" + n1.get(0)).charAt(0) < ("" + n2.get(0)).charAt(0))) {
                                if (n1pos) {
                                    asExpression += "+" + addSpaces(n1.get(0));
                                } else {
                                    asExpression += "-" + addSpaces(n1.get(0));
                                }
                                n1.remove(0);
                            } else {
                                if (n1pos) {
                                    asExpression += "-" + addSpaces(n2.get(0));
                                } else {
                                    asExpression += "+" + addSpaces(n2.get(0));
                                }
                                n2.remove(0);
                            }
                        }
                        System.out.println(asExpression);
                        output.add(asExpression);
                    }
                }
            }
        }
        
        Collections.sort(output);
        PrintWriter p = new PrintWriter(new FileWriter("zerosum.out"));
        for (String s : output) {
            p.println(s);
        }
        p.close();
        

    }

    static String addSpaces(int i) {
        String s = "" + i % 10;
        i /= 10;
        while (i > 0) {
            s = i % 10 + " " + s;
            i /= 10;
        }
        return s;
    }

    static boolean isMulti(int i) {
        if (i <= N) {
            return true;
        }
        int d = i % 10;
        if (d > N) {
            return false;
        }
        i /= 10;
        int nd = i % 10;
        return nd + 1 == d && isMulti(i);
    }

    static int asCode(int i) {
        int o = 0;
        while (i > 0) {
            switch (i % 10) {
                case 1:
                    o |= ONE;
                    break;
                case 2:
                    o |= TWO;
                    break;
                case 3:
                    o |= THREE;
                    break;
                case 4:
                    o |= FOUR;
                    break;
                case 5:
                    o |= FIVE;
                    break;
                case 6:
                    o |= SIX;
                    break;
                case 7:
                    o |= SEVEN;
                    break;
                case 8:
                    o |= EIGHT;
                    break;
                case 9:
                    o |= NINE;
                    break;
            }
            i /= 10;
        }
        return o;
    }

    static void sort(List<Integer> l, int low, int high) {
        if (high - low <= 0) {
            return;
        }
        int mid = (low + high) / 2;
        sort(l, low, mid);
        sort(l, mid + 1, high);
        ArrayList<Integer> temp = new ArrayList<>();
        int i = low;
        int j = mid + 1;
        while (i <= mid || j <= high) {
            if (i > mid) {
                temp.add(l.get(j++));
            } else if (j > high) {
                temp.add(l.get(i++));
            } else {

                int fi = ("" + l.get(i)).charAt(0);
                int fj = ("" + l.get(j)).charAt(0);

                if (fj > fi) {
                    temp.add(l.get(i++));
                } else {
                    temp.add(l.get(j++));
                }
            }
        }
        for (int a = low; a <= high; a++) {
            l.set(a, temp.get(a - low));
        }
    }
}

class number {

    int digits;
    ArrayList<Integer> numbers;

    number(int d, ArrayList<Integer> n) {
        digits = d;
        numbers = n;
    }

    @Override
    public boolean equals(Object o) {
        number n = (number) o;
        if (n.numbers.size() != numbers.size()) {
            return false;
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (!Objects.equals(n.numbers.get(i), numbers.get(i))) {
                return false;
            }
        }
        return n.digits == digits;
    }

    public String toString() {
        return digits + ", " + numbers;
    }
}
