/**
 * Solution.java
 * 24/08/2012
 * @author Diego Garcia
 *
 */
package me.truenite.Candies;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input01.txt");
        Scanner sc = new Scanner(file);
        // sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // reading input
            int N = sc.nextInt();
            int[][] kids = new int[N][3];
            int candy = 0;
            for (int i = 0; i < N; i++) {
                kids[i][0] = sc.nextInt();
                kids[i][1] = 1;
                kids[i][2] = 0;
            }
            int pleased = 0;
            for (int i = 1; i < N - 1; i++) {
                if (kids[i][0] <= kids[i - 1][0]
                        && kids[i][0] <= kids[i + 1][0]) {
                    kids[i][2] = 1;
                }
            }
            int count = 0;
            while (pleased < N) {
                count++;
                for (int i = 1; i < N - 1; i++) {
                    if (kids[i][2] != 1) {
                        if (kids[i][0] > kids[i - 1][0]
                                && kids[i][1] <= kids[i - 1][1])
                            kids[i][1] = kids[i - 1][1] + 1;
                        if (kids[i][0] > kids[i + 1][0]
                                && kids[i][1] <= kids[i + 1][1]) {
                            kids[i][1] = kids[i + 1][1] + 1;
                        }
                        if (kids[i][0] > kids[i - 1][0]
                                && kids[i][0] > kids[i + 1][0]) {
                            if (kids[i - 1][2] == 1 && kids[i + 1][2] == 1
                                    && kids[i][1] > kids[i - 1][1]
                                    && kids[i][1] > kids[i + 1][1])
                                kids[i][2] = 1;
                        } else {
                            if (kids[i][0] > kids[i - 1][0]) {
                                if (kids[i - 1][2] == 1
                                        && kids[i][1] > kids[i - 1][1])
                                    kids[i][2] = 1;
                            }
                            if (kids[i][0] > kids[i + 1][0]) {
                                if (kids[i + 1][2] == 1
                                        && kids[i][1] > kids[i + 1][1])
                                    kids[i][2] = 1;
                            }
                        }
                    }
                }
                if (kids[0][0] > kids[1][0])
                    kids[0][1] = kids[1][1] + 1;
                kids[0][2] = 1;
                if (kids[kids.length - 1][0] > kids[kids.length - 2][0])
                    kids[kids.length - 1][1] = kids[kids.length - 2][1] + 1;
                kids[kids.length - 1][2] = 1;
                pleased = 0;
                for (int i = 0; i < N; i++) {
                    pleased += kids[i][2];
                }
            }
            // output
            for (int i = 0; i < N; i++) {
                candy += kids[i][1];
            }
            System.out.println(candy);
        }
    }
}
