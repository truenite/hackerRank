/**
 * Solution.java
 * 28/08/2012
 * @author Diego Garcia
 *
 */
package me.truenite.EvenTree;

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
         File file = new File("input00.txt");
         Scanner sc = new Scanner(file);
         // sc = new Scanner(System.in);
         if (sc.hasNext()) {
             // reading input
             int N = sc.nextInt();
             int M = sc.nextInt();
             int[] parents = new int[N];
             int root = 0;
             @SuppressWarnings("unchecked")
             LinkedList<Integer>[] graph = (LinkedList<Integer>[]) new LinkedList[N];
             for (int i = 0; i < N; i++) {
                 graph[i] = new LinkedList<Integer>();
                 parents[i] = -1;
             }
             for (int i = 0; i < M; i++) {
                 int a = sc.nextInt();
                 int b = sc.nextInt();
                 graph[b - 1].add(a - 1);
                 parents[a - 1] = b - 1;
             }
             for (int i = 0; i < N; i++)
                 if (parents[i] == -1)
                     root = i;
             int[] removed = new int[1];
             pairs(graph, parents, root, removed);
             System.out.println(removed[0]);
         }

     }

     private static int pairs(LinkedList<Integer>[] graph, int[] parents,
             int root, int[] removed) {
         int descendants = 1;
         if (graph[root].isEmpty()) {
             return 1;
         } else {
             int i = 0;
             for (i = 0; i < graph[root].size(); i++) {
                 int temp = pairs(graph, parents, graph[root].get(i), removed);
                 if (temp == 0)
                     i--;
                 else
                     descendants += temp;
             }
             if (descendants % 2 == 0 && parents[root] != -1) {
                 removed[0]++;
                 graph[parents[root]].removeFirstOccurrence(root);
                 parents[root] = -1;
                 return 0;
             }
         }
         return descendants;
     }
 }
