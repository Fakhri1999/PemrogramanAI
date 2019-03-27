package Praktikum;
import java.util.*;
public class MapColoring extends CSPProblem {
    int boardSize = 8;
    List<Variable> tiles;
    List<Constraint> constraints;
    List<Object> domain = new LinkedList<Object>();
    public MapColoring(int size) {
        boardSize = size;
        for (int i = 1; i <= boardSize; i++) {
            domain.add(i);
        }
        tiles = new ArrayList<Variable>();
        constraints = new ArrayList<Constraint>();
    }
    public class Map extends Variable {
        public String name;
        public Map(String name) {
            this.name = name;
        }
        public String description() {
            return name;
        }
        public List<Object> domain() {
            return domain;
        }
    }
    public class AllDiff extends Constraint {
        public List<Variable> variables = new LinkedList<Variable>();
        public boolean satisfied(Assignment asign) {
            boolean[] found = new boolean[boardSize + 1];
            for (Variable v : variables) {
                Integer val = (Integer) asign.getValue(v);
                if (val == null) {
                    return false;
                }
                found[val] = true;
            }
            return true;
        }
        public boolean consistent(Assignment asign) {
            boolean[] found = new boolean[boardSize + 1];
            boolean[] available = new boolean[boardSize + 1];
            int constraintDomain = 0;
            for (Variable v : variables) {
                for (Object val : domainValues(asign, v)) {
                    if (!available[(Integer) val]) {
                        constraintDomain++;
                        available[(Integer) val] = true;
                    }
                }
                Integer val = (Integer) asign.getValue(v);
                if (val != null) {
                    if (found[val]) {
                        return false;
                    }
                    found[val] = true;
                }
            }
            if (variables.size() > constraintDomain) {
                return false;
            }
            return true;
        }
        public List<Variable> reliesOn() {
            return variables;
        }
        public String description() {
            String desc = "Constraint on {\n";
            for (Variable v : variables) {
                desc += "\t" + v.description() + "\n";
            }
            desc += "}";
            return desc;
        }
    }
    public List<Variable> variables() {
        if (tiles.isEmpty()) {
            tiles.add(new Map("Aceh"));
            tiles.add(new Map("Sumatera Utara"));
            tiles.add(new Map("Riau"));
            tiles.add(new Map("Sumatera Barat"));
            tiles.add(new Map("Jambi"));
            tiles.add(new Map("Bengkulu"));
            tiles.add(new Map("Sumatera Selatan"));
            tiles.add(new Map("Lampung"));
        }
        return tiles;
    }
    public List<Constraint> constraints() {
        if (constraints.isEmpty()) {
            AllDiff constr;
            //Aceh->Sumatera Utara 
            constr = new AllDiff();
            constr.variables.add(tiles.get(0));
            constr.variables.add(tiles.get(1));
            constraints.add(constr);
            //Sumatera Utara->Riau 
            constr = new AllDiff();
            constr.variables.add(tiles.get(1));
            constr.variables.add(tiles.get(2));
            constraints.add(constr);
            //Sumatera Utara->Sumatera Barat 
            constr = new AllDiff();
            constr.variables.add(tiles.get(1));
            constr.variables.add(tiles.get(3));
            constraints.add(constr);
            //Riau->Jambi
            constr = new AllDiff();
            constr.variables.add(tiles.get(2));
            constr.variables.add(tiles.get(4));
            constraints.add(constr);
            //Riau->Sumatera Barat 
            constr = new AllDiff();
            constr.variables.add(tiles.get(2));
            constr.variables.add(tiles.get(3));
            constraints.add(constr);
            //Sumatera Barat->Jambi 
            constr = new AllDiff();
            constr.variables.add(tiles.get(3));
            constr.variables.add(tiles.get(4));
            constraints.add(constr);
            //Sumatera Barat->Bengkulu 
            constr = new AllDiff();
            constr.variables.add(tiles.get(3));
            constr.variables.add(tiles.get(5));
            constraints.add(constr);
            //Jambi->Sumatera Selatan 
            constr = new AllDiff();
            constr.variables.add(tiles.get(4));
            constr.variables.add(tiles.get(6));
            constraints.add(constr);
            //Bengkulu->Sumatera Selatan 
            constr = new AllDiff();
            constr.variables.add(tiles.get(5));
            constr.variables.add(tiles.get(6));
            constraints.add(constr);
            //Bengkulu->Jambi
            constr = new AllDiff();
            constr.variables.add(tiles.get(5));
            constr.variables.add(tiles.get(4));
            constraints.add(constr);
            //Sumatera Selatan->Lampung 
            constr = new AllDiff();
            constr.variables.add(tiles.get(6));
            constr.variables.add(tiles.get(7));
            constraints.add(constr);
        }
        return constraints;
    }
}