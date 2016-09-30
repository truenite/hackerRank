/**
 * Solution.java
 * 27/08/2012
 * @author Diego Garcia
 *
 */
package me.truenite.Pairs;

import java.io.*;
import java.util.*;


/**
 *
 */
 public class Solution {

     /**
      * @param args
      * @throws FileNotFoundException
      */
     public static void main(String[] args) throws FileNotFoundException {
         File file = new File("input02.txt");
         Scanner sc = new Scanner(file);
         // sc = new Scanner(System.in);
         if (sc.hasNext()) {
             // reading input
             int N = sc.nextBigInteger().intValue();
             int K = sc.nextBigInteger().intValue();
             Hashtable<Integer, Integer> nums = new Hashtable<Integer, Integer>();
             for (int i = 0; i < N; i++) {
                 nums.put(sc.nextBigInteger().intValue(), 0);
             }
             int pairs = 0;
             Enumeration<Integer> enumera = nums.keys();
             while (enumera.hasMoreElements()) {
                 int num = enumera.nextElement();
                 nums.remove(num);
                 int plus = num + K;
                 int rest = num - K;
                 if (nums.containsKey(plus))
                     pairs++;
                 if (nums.containsKey(rest))
                     pairs++;
             }
             System.out.println(pairs);
         }

     }
 }
