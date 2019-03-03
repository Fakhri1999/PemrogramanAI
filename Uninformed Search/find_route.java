import java.util.*;

class Node implements Comparator<Node> {

    public int node;
    public int cost;
    public String name;

    public Node() {

    }

    public Node(String name) {
        this.name = name;
    }

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.cost < node2.cost) {
            return -1;
        }
        if (node1.cost > node2.cost) {
            return 1;
        }
        if (node1.node < node2.node) {
            return -1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            if (this.node == node.node) {
                return true;
            }
        }
        return false;
    }
}

class UniformCostSearch {

    private PriorityQueue<Node> priorityQueue;
    private Set<Integer> terkunjungi;
    private int jarak[];
    private int numberOfNodes;
    private int adjacencyMatrix[][];
    public LinkedList<Integer> jalur;
    private int[] parent;
    private int asal, tujuan;
    public static final int MAX_VALUE = 999999;
    find_route kota = new find_route();

    public UniformCostSearch(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.terkunjungi = new HashSet<Integer>();
        this.priorityQueue = new PriorityQueue<Node>(numberOfNodes, new Node());
        this.jarak = new int[numberOfNodes + 1];
        this.jalur = new LinkedList<Integer>();
        this.adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];
        this.parent = new int[numberOfNodes + 1];
    }

    public int uniformCostSearch(int adjacencyMatrix[][], int asal, int tujuan) {
        int evaluationNode;
        this.asal = asal;
        this.tujuan = tujuan;
        for (int i = 1; i <= numberOfNodes; i++) {
            jarak[i] = MAX_VALUE;
        }
        for (int asalvertex = 1; asalvertex <= numberOfNodes; asalvertex++) {
            for (int tujuanvertex = 1; tujuanvertex <= numberOfNodes; tujuanvertex++) {
                this.adjacencyMatrix[asalvertex][tujuanvertex]
                        = adjacencyMatrix[asalvertex][tujuanvertex];
            }
        }
        priorityQueue.add(new Node(asal, 0));
        jarak[asal] = 0;        
        long start = System.currentTimeMillis();
        long stop = 0;
        int status = 0;
        while (!priorityQueue.isEmpty()) {
            evaluationNode = getNodeWithMinJarakFromPriorityQueue();
            addJalur(evaluationNode);
            if(status==0 && jalur.size()>1){
                jalur.clear();
                addJalur(evaluationNode);
                System.out.println("Mencari jalur lain ...");
                System.out.println("Jarak : " + jarak[evaluationNode]);
                System.out.print("Jalur : ");
                cetakJalur();
                jalur.clear();
                System.out.println();
            } else if(status==0 && jalur.size()==1){
                jalur.clear();
                addJalur(evaluationNode);
                System.out.println("Jarak : " + jarak[evaluationNode]);
                System.out.print("Jalur : ");
                cetakJalur();
                jalur.clear();
                System.out.println();
            }
            if (evaluationNode == tujuan) {
                status = 1;
            }
            stop = System.currentTimeMillis();
            jalur.clear();
            terkunjungi.add(evaluationNode);
            addFrontiersToQueue(evaluationNode);
        }
        System.out.println("Jalur telah ditemukan");
        if (status == 1) {
            return jarak[tujuan];
        } else {
            return 999;
        }
    }

    private void addFrontiersToQueue(int evaluationNode) {
        for (int tujuan = 1; tujuan <= numberOfNodes; tujuan++) {
            if (!terkunjungi.contains(tujuan)) {
                if (adjacencyMatrix[evaluationNode][tujuan] != 999999) {
                    if (jarak[tujuan] > adjacencyMatrix[evaluationNode][tujuan]
                            + jarak[evaluationNode]) {
                        jarak[tujuan] = adjacencyMatrix[evaluationNode][tujuan]
                                + jarak[evaluationNode];
                        parent[tujuan] = evaluationNode;
                    }
                    Node node = new Node(tujuan, jarak[tujuan]);
                    if (priorityQueue.contains(node)) {
                        priorityQueue.remove(node);
                    }
                    priorityQueue.add(node);
                }
            }
        }
    }

    private int getNodeWithMinJarakFromPriorityQueue() {
        Node node = priorityQueue.remove();
        return node.node;
    }

    public void addJalur(int tujuan) {
        jalur.add(tujuan);
        boolean found = false;
        int temp = tujuan;
        while (!found) {
            if (temp == asal) {
                found = true;
                continue;
            }
            jalur.add(parent[temp]);
            temp = parent[temp];
        }
    }
    
    public void cetakJalur(){
        for (int i = jalur.size()-1; i >= 0; i--) {
            if(i==0)
                System.out.println(kota.city.get(jalur.get(i)-1));
            else
                System.out.print(kota.city.get(jalur.get(i)-1) + " -> ");
        }
    }
}

public class find_route {

    public static ArrayList<String> city;
    public static int[][] adjacencyMatrix;

    private static int getIndex(String name) {
        int p = -1;
        if (city.contains(name)) {
            for (int i = 0; i < city.size(); i++) {
                if (city.get(i).equals(name)) {
                    return i + 1;
                }
            }
        }
        return p;
    }
    
    private static void insertJarak(String asal, String tujuan, int jarak){
        int i = getIndex(asal);
        int j = getIndex(tujuan);
        adjacencyMatrix[j][i] = adjacencyMatrix[i][j] = jarak;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        city = new ArrayList<String>();
        city.add("A");city.add("B");
        city.add("C");city.add("D");
        city.add("E");city.add("F");
        city.add("G");city.add("H");
        city.add("I");city.add("J");
        city.add("K");city.add("L");
        city.add("M");city.add("N");
        city.add("O");city.add("P");
        city.add("Q");city.add("R");
        city.add("S");city.add("T");
        city.add("U");city.add("V");
        city.add("X");
        adjacencyMatrix = new int[city.size() + 1][city.size() + 1];
        insertJarak("A", "B", 145);
        insertJarak("A", "I", 148);
        insertJarak("A", "X", 55);
        insertJarak("A", "V", 167);
        insertJarak("B", "A", 145);
        insertJarak("B", "C", 98);
        insertJarak("C", "B", 98);
        insertJarak("C", "D", 212);
        insertJarak("D", "C", 212);
        insertJarak("D", "E", 102);
        insertJarak("D", "K", 102);
        insertJarak("E", "D", 102);
        insertJarak("E", "F", 152);
        insertJarak("E", "L", 95);
        insertJarak("E", "M", 73);
        insertJarak("E", "N", 97);
        insertJarak("F", "E", 152);
        insertJarak("F", "G", 83);
        insertJarak("G", "F", 83);
        insertJarak("G", "H", 75);
        insertJarak("H", "G", 75);
        insertJarak("I", "A", 148);
        insertJarak("I", "J", 75);
        insertJarak("I", "Q", 25);
        insertJarak("J", "I", 75);
        insertJarak("J", "K", 95);
        insertJarak("K", "J", 95);
        insertJarak("K", "D", 102);
        insertJarak("L", "E", 95);
        insertJarak("M", "E", 73);
        insertJarak("N", "E", 97);
        insertJarak("N", "P", 65);
        insertJarak("N", "O", 25);
        insertJarak("O", "N", 25);
        insertJarak("P", "N", 65);
        insertJarak("Q", "I", 25);
        insertJarak("Q", "X", 30);
        insertJarak("R", "S", 93);
        insertJarak("R", "X", 57);
        insertJarak("S", "R", 93);
        insertJarak("S", "T", 112);
        insertJarak("T", "S", 112);
        insertJarak("T", "V", 25);
        insertJarak("T", "U", 75);
        insertJarak("U", "T", 75);
        insertJarak("U", "V", 85);
        insertJarak("V", "U", 85);
        insertJarak("V", "T", 25);
        insertJarak("V", "A", 167);
        insertJarak("X", "A", 55);
        insertJarak("X", "R", 57);
        insertJarak("X", "Q", 30);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == 0) {
                    adjacencyMatrix[i][j] = 999999;
                }
            }
        }

        UniformCostSearch ucs = new UniformCostSearch(city.size());
        System.out.print("Kota asal = ");
        String asal = in.next();
        System.out.print("Kota tujuan = ");
        String tujuan = in.next();
        if (city.contains(asal) && city.contains(tujuan)) {
            int src = getIndex(asal);
            int dst = getIndex(tujuan);
            int jarak = ucs.uniformCostSearch(adjacencyMatrix, src, dst);
            if (jarak == 99999999) {
                System.out.println();
                System.out.println("Jarak : INFINITY");
                System.out.println("Jalur : NONE");
            } else {
                ucs.addJalur(dst);
                System.out.println();
                System.out.println("Jarak : " + jarak);
                System.out.print("Jalur : ");
                ucs.cetakJalur();
            }
        } else {
            System.out.println();
            System.out.println("Kota Tidak Ditemukan");
        }
    }
}