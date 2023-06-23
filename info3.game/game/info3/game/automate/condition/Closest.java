package info3.game.automate.condition;

import info3.game.entity.Entity;

public class Closest extends Condition{

    public Closest(boolean notOp) {
        super(notOp);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean eval(Entity e) {
        System.out.println("We do not use Closest, so in order to not have a problem with the parser, it always return false");
        return false;
    }
    
}
