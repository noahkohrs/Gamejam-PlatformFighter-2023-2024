package info3.game.automate.condition;

import info3.game.entity.Entity;

public abstract class Condition {
    public Condition(boolean notOp) {
        this.notOp = false;
    }
    public boolean notOp;
    public abstract boolean eval(Entity e);
    public boolean affectNotOp(boolean res) {
        if (notOp)
            return !res;
        return res;
    }
}
