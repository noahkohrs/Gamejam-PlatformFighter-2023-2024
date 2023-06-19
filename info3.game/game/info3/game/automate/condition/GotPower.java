package info3.game.automate.condition;

import info3.game.entity.Entity;

public class GotPower extends Condition {
    public boolean m_gotPower;

    public GotPower(boolean notOp) {
        super(notOp);
        m_gotPower = false;
    }

    public GotPower() {
        this(false);
    }

    @Override
    public boolean eval(Entity e) {
        return affectNotOp(e.gotPower());
    }
    
}