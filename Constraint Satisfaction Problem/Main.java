package Praktikum;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public MapColoring coloring;
    public Assignment initial = Assignment.blank();

    public Main(int size) {
        initial = Assignment.blank();
        coloring = new MapColoring(size);
    }

    public Assignment solve() {
        MRVBacktrack mb = new MRVBacktrack(coloring, initial);
        return mb.solve();
    }
    public static void main(String[] args) {
        List<String> color = new ArrayList<String>();
        color.add("Merah");
        color.add("Biru");
        color.add("Hijau");
        Main m = new Main(color.size());
        System.out.println("================== Coloring Map Peta Pulau Sumatera ==================");
        for (int i = 0; i < color.size(); i++) {
            System.out.println("Warna " + (i + 1) + "\t\t: " + color.get(i));
        }
        System.out.println("Constraint \t: Provinsi yang berdampingan tidak memiliki warna yang sama\n");
        Assignment solution = m.solve();
        if (!(solution == null)) {
            System.out.println("------------Hasil------------");
            List<Variable> var = m.coloring.variables();
            for (Variable v : var) {
                System.out.printf("%-17s : %s\n",
                        v.description(), color.get(((Integer) solution.getValue(v)) - 1));
            }
        } else {
            System.out.println("Solusi tidak ditemukan");
        }
    }
}