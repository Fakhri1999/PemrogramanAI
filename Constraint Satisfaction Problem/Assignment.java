package Praktikum;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class Assignment
{
    Map<Variable, Object> assignments = null;
    Map<Variable, List<Object>> domain = null;
    static Assignment blank()
    {
        Assignment blank = new Assignment();
        blank.assignments = new HashMap<>();
        blank.domain = new HashMap<>();
        return blank;
    }
    public Assignment assign(Variable v, Object val)
    {
        Assignment n = new Assignment();
        n.assignments = new HashMap<>(assignments);
        n.assignments.put(v, val);
        n.domain = new HashMap<>(domain);

        List<Object> varDomain = new LinkedList<Object>();
        varDomain.add(val);
        n.restrictDomain(v, varDomain);

        return n;
    }
    public Object getValue(Variable v)
    {
        return assignments.get(v);
    }
    public void restrictDomain(Variable v, List<Object> dom)
    {
        domain.put(v, dom);
    }
    public List<Object> getDomain(Variable v)
    {
        return domain.get(v);
    }
    public int size()
    {
        return assignments.size();
    }
}