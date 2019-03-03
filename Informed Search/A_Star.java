package Praktikum;

import java.util.*;

class Node {

    String nama;
    ArrayList<Node> tetangga = new ArrayList<Node>();
    ArrayList<Integer> cost = new ArrayList<Integer>();
    int JarakGoal;

    public Node(String nama, int jarak) {
        this.nama = nama;
        this.JarakGoal = jarak;
    }

    void addTetangga(Node tetangga, int cost) {
        this.tetangga.add(tetangga);
        this.cost.add(cost);
    }

    Node closest() {
        int hasil = smallest(tetangga, cost);
        Node hasil2 = tetangga.get(hasil);
        int gn = cost.get(hasil);
        int hn = hasil2.JarakGoal;
        int fn = gn + hn;
        System.out.println("Kota " + tetangga.get(hasil).nama);
        System.out.printf("f(n) = g(n) + h(n) = %d + %d = %d\n\n", gn, hn, fn);

        return tetangga.get(hasil);
    }

    int smallest(ArrayList<Node> tetangga, ArrayList<Integer> cost) {
        int temp = 0;

        for (int i = 1; i < tetangga.size(); i++) {
            if (tetangga.get(i).JarakGoal + cost.get(i) < tetangga.get(temp).JarakGoal + cost.get(temp)) {
                temp = i;
            }
        }

        return temp;
    }
}

public class A_Star {

    public static void main(String[] args) {
        ArrayList<Node> path = new ArrayList<Node>();
        Node n1 = new Node("A", 62);
        Node n2 = new Node("B", 48);
        Node n3 = new Node("C", 32);
        Node n4 = new Node("D", 0);
        Node n5 = new Node("E", 24);
        Node n6 = new Node("F", 44);
        Node n7 = new Node("G", 42);

        n1.addTetangga(n2, 11);
        n1.addTetangga(n7, 10);
        n1.addTetangga(n6, 15);
        n2.addTetangga(n3, 19);
        n2.addTetangga(n1, 11);
        n2.addTetangga(n7, 10);
        n2.addTetangga(n4, 23);
        n3.addTetangga(n2, 19);
        n3.addTetangga(n4, 15);
        n4.addTetangga(n3, 15);
        n4.addTetangga(n2, 23);
        n4.addTetangga(n6, 21);
        n4.addTetangga(n5, 11);
        n5.addTetangga(n4, 11);
        n5.addTetangga(n6, 13);
        n6.addTetangga(n5, 13);
        n6.addTetangga(n4, 21);
        n6.addTetangga(n7, 7);
        n6.addTetangga(n1, 15);
        n7.addTetangga(n6, 7);
        n7.addTetangga(n1, 10);
        n7.addTetangga(n2, 10);

        Node n = n1;
        path.add(n);
        while (n.JarakGoal != 0) {
            n = n.closest();
            path.add(n);
        }
        
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                System.out.println(path.get(i).nama);
            } else {
                System.out.print(path.get(i).nama + " -> ");
            }
        }
    }
}