package info3.game.automate.condition;

import info3.game.entity.Entity;

public class True extends Condition{

    @Override
    public boolean eval(Entity e) {
        return true;
    }
    
}
