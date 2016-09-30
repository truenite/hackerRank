/**
 * Solution.java
 * 04/12/2012
 * @author Diego Garcia
 *
 */
package me.truenite.Median;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 */
 public class Solution {
    static int size = 0;

    /**
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input00.txt");
        Scanner sc = new Scanner(file);
        // sc = new Scanner(System.in);
        if (sc.hasNext()) {
            int N = sc.nextInt();
            String operation;
            ArrayList<Integer> array = new ArrayList<Integer>();
            int number;
            for (int i = 0; i < N; i++) {
                operation = sc.next();
                number = sc.nextInt();
                operate(array, operation, number);
            }
        }

    }

    public static void operate(ArrayList<Integer> array, String operation,
            Integer n) {
        if (operation.equals("r")) {
            if (size <= 1 | !remove(array, n)) {
                System.out.println("Wrong!");
                return;
            }
        } else {
            splitOrderedInsert(array, n);
            size++;
        }
        double M = 0;

        if (size % 2 == 0) {
            double a = array.get(((size / 2) - 1));
            double b = array.get((size / 2));
            M = (a + b) / 2;
        } else {
            M = array.get(size / 2);
        }
        DecimalFormat df = new DecimalFormat("####.###");
        System.out.println(df.format(M));
    }

    public static void orderedInsert(ArrayList<Integer> array, int n) {
        int i = 0;
        for (; i < size; i++) {
            if (array.get(i) >= n)
                break;
        }
        array.add(i, n);
    }

    public static void splitOrderedInsert(ArrayList<Integer> array, int n) {
        int i = 0;
        int half = size / 2;
        if (size == 0) {
            array.add(n);
            return;
        }
        if (array.get(size - 1) <= n) {
            array.add(n);
            return;
        }
        for (; i < size - 1; i++) {
            if (array.get(i) >= n)
                break;
        }
        array.add(i, n);
    }

    public static void binaryOrderedInsert(ArrayList<Integer> array, int n) {
        if (size == 0) {
            array.add(n);
            return;
        }
        if (size == 1) {
            if (array.get(0) > n)
                array.add(0, n);
            else
                array.add(n);
            return;
        }
        binaryOrderedInsertIterative(array, n, 0, size);
    }

    public static void binaryOrderedInsertRecursive(ArrayList<Integer> array,
            int n, int begin, int end) {
        int half = end - begin;
        if (begin == 0)
            half--;
        if (half <= 0) {
            array.add(half, n);
            return;
        }
        int atHalf = array.get(half);
        if (atHalf == n) {
            array.add(half, n);
            return;
        }
        if (atHalf < n) {
            binaryOrderedInsertRecursive(array, n, half, end);
        }
        if (atHalf > n) {
            binaryOrderedInsertRecursive(array, n, begin, half);
        }
    }

    public static void binaryOrderedInsertIterative(ArrayList<Integer> array,
            int n, int begin, int end) {
        int half = end - begin;
        while (true) {
            if (begin == 0)
                half--;
            if (half <= 0) {
                array.add(half, n);
                return;
            }
            int atHalf = array.get(half);
            if (atHalf < n) {
                begin = half;
            }
            if (atHalf > n) {
                end = half;
            }
            half = end - begin;
        }
    }

    public static boolean contains(ArrayList<Integer> array, int n) {
        int begin = 0;
        int end = size - 1;
        int half = ((end - begin) / 2) + begin;
        if (size == 0)
            return false;
        if (size == 1 && array.get(0) == n)
            return true;
        while (true) {
            int atHalf = array.get(half);
            if (atHalf == n)
                return true;
            if (half == begin) {
                atHalf = array.get(half + 1);
                if (atHalf == n)
                    return true;
                else
                    return false;
            }
            if (end == begin)
                return false;
            if (atHalf < n) {
                begin = half;
            }
            if (atHalf > n) {
                end = half;
            }
            half = ((end - begin) / 2) + begin;
            System.out.println(
                    "ebegin:" + begin + " half: " + half + "  end: " + end);
        }
    }

    public static boolean remove(ArrayList<Integer> array, int n) {
        int begin = 0;
        int end = size - 1;
        int half = ((end - begin) / 2) + begin;
        if (size == 0)
            return false;
        if (size == 1 && array.get(0) == n) {
            array.remove(0);
            size--;
            return true;
        }
        while (true) {
            int atHalf = array.get(half);
            if (atHalf == n) {
                array.remove(half);
                size--;
                return true;
            }
            if (end == begin)
                return false;
            if (half == begin) {
                atHalf = array.get(half + 1);
                if (atHalf == n) {
                    array.remove(half + 1);
                    size--;
                    return true;
                } else
                    return false;
            }
            if (atHalf < n) {
                begin = half;
            }
            if (atHalf > n) {
                end = half;
            }
            half = ((end - begin) / 2) + begin;
        }
    }
}
