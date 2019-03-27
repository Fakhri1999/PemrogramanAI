package Praktikum;

import java.util.List;

public class MRVBacktrack extends Backtrack {

    public MRVBacktrack(CSPProblem problem, Assignment initial) {
        super(problem, initial);
    }
    protected Variable unassignedVar(Assignment assign) {
        int minDomain = Integer.MAX_VALUE;
        Variable minVar = null;
        List<Variable> vars = problem.variables();
        if (vars.size() == assign.size()) {
            return null;
        }

        for (Variable v : vars) {
            if (assign.getValue(v) == null) {
                int domSize
                        = problem.domainValues(assign, v).size();
                if (minDomain > domSize) {
                    minDomain = domSize;
                    minVar = v;
                }
            }
        }

        return minVar;
    }
    class ValueAssignment implements
            Comparable<ValueAssignment> {

        public Integer domain = 0;
        public Object value;

        public ValueAssignment(Assignment init, Variable v, Object val) throws IllegalStateException {
            value = val;
            Assignment newAssign = init.assign(v, val);

            if (!problem.consistentAssignment(newAssign, v)) {
                throw new IllegalStateException("Bad Value ");
            }
            newAssign = problem.inference(newAssign, v);
            for (Variable var : problem.variables()) {
                domain += problem.domainValues(newAssign, var).size();
            }
        }
        public int compareTo(ValueAssignment other) {
            return domain.compareTo(other.domain);
        }
    }
}