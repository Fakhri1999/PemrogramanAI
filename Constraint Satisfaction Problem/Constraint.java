package Praktikum;

import java.util.List;
public abstract class Constraint
{
    public abstract String description();
    public abstract boolean satisfied(Assignment asgn);
    public abstract boolean consistent(Assignment asgn);
    public abstract List<Variable> reliesOn();
}
