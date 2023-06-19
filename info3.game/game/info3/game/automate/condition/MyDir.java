package info3.game.automate.condition;

import info3.game.entity.Entity;

public class MyDir extends Condition{
    String direction;
    public MyDir(boolean notOp) {
        super(notOp);
    }

    public MyDir(String direction){
        super(false);
        this.direction=direction;
    }
    @Override
    public boolean eval(Entity e) {
        return e.MyDir(direction);
    }
    
}
