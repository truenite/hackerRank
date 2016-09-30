/**
 * Solution.java
 * 01/09/2012
 * @author Diego Garcia
 *
 * This one was is called GreedyFlorist now
 */
package me.truenite.Flowers;

import java.io.*;
import java.util.*;;

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
            int K = sc.nextInt();
            int[] flowers = new int[N];
            TreeSet<Integer> flows = new TreeSet<Integer>();
            int[][] buyers = new int[K][2]; // K[][0] how many flowers K[][1]
                                            // money spent
            TreeSet<Buyer> buys = new TreeSet<Buyer>();
            for (int i = 0; i < K; i++) {
                buys.add(new Buyer(i));
            }
            for (int i = 0; i < N; i++) {
                flows.add(sc.nextInt());
            }
            int spent = 0;
            while (flows.size() != 0) {
                int temp = flows.last();
                flows.remove(flows.last());
                int tempSpent = (buys.first().getBought() + 1) * temp;
                spent += tempSpent;
                buys.first().setSpent(buys.first().getSpent() + tempSpent);
                add(buys, 1);
            }
            System.out.println(spent);

        }
    }

    static void add(TreeSet<Buyer> buys, int n) {
        Buyer temp = buys.first();
        buys.remove(buys.first());
        temp.setBought(temp.getBought() + n);
        buys.add(temp);
    }
}

class Buyer implements Comparable<Buyer> {
    int bought;
    int spent;
    int id;

    public Buyer(int id) {
        this.id = id;
        spent = 0;
        bought = 0;
    }

    protected int getId() {
        return id;
    }

    protected int getSpent() {
        return spent;
    }

    protected void setSpent(int spent) {
        this.spent = spent;
    }

    protected int getBought() {
        return bought;
    }

    protected void setBought(int bought) {
        this.bought = bought;
    }

    @Override
    public int compareTo(Buyer o) {
        if (this.bought > o.getBought())
            return 1;
        if (this.bought < o.getBought())
            return -1;
        if (this.id > o.getId())
            return 1;
        if (this.id < o.getId())
            return -1;
        return 0;
    }

    public String toString() {
        return "{" + id + ", " + bought + ", " + spent + "}";
    }

    public boolean equals(Object o) {
        if (id == ((Buyer) o).getId())
            return true;
        return false;
    }
}
