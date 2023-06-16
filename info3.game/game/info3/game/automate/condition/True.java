package info3.game.automate.condition;

import info3.game.entity.Entity;

public class True extends Condition{

    public True(boolean notOp) {
        super(notOp);
    }
    public True() {
        this(false);
    }
    @Override
    public boolean eval(Entity e) {
        return affectNotOp(true);
    }
    
}
