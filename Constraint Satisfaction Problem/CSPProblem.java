package Praktikum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public abstract class CSPProblem
{
    Map<Variable, List<Constraint>> varConstraints = null;
    public abstract List<Variable> variables();
    public abstract List<Constraint> constraints();
    public boolean satisfiedByAssignment(Assignment asign)
    {
        if(variables().size() > asign.size())
        {
            return false;
        }
        for(Constraint c : constraints())
        {
            if(!c.satisfied(asign))
            {
                return false;
            }
        }

        return true;
    }
    public List<Constraint> variableConstraints(Variable v)
    {
        if(varConstraints != null)
        {
            return varConstraints.get(v);
        }
        varConstraints = new HashMap<Variable, List<Constraint>>();
        for(Constraint c : constraints())
        {
            List<Variable> vars = c.reliesOn();
            for(Variable constrVar : vars)
            {
                if(varConstraints.containsKey(constrVar))
                {
                    varConstraints.get(constrVar).add(c);
                }
                else
                {
                    List<Constraint> constr = new LinkedList<Constraint>();
                    constr.add(c);
                    varConstraints.put(constrVar, constr);
                }
            }
        }
        return varConstraints.get(v);
    }
    public List<Object> domainValues(Assignment assign, Variable v)
    {
        List<Object> domain = assign.getDomain(v);
        if(domain != null)
        {
            return domain;
        }
        return v.domain();
    }
    public boolean consistentAssignment(Assignment assign, Variable v)
    {
        for(Constraint c : constraints())
        {
            if(!c.consistent(assign))
            {
                return false;
            }
        }

        return true;
    }
    public Assignment inference(Assignment assign, Variable v) throws IllegalStateException
    {
        return assign;
    }
}
