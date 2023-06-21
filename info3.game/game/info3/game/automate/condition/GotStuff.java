package info3.game.automate.condition;

import info3.game.entity.Entity;

public class GotStuff extends Condition {
    public boolean m_gotStuff;

    public GotStuff(boolean notOp) {
        super(notOp);
        m_gotStuff = false;
    }

    public GotStuff() {
        this(false);
    }

    @Override
    public boolean eval(Entity e) {
        return affectNotOp(e.gotStuff());
    }
    
}