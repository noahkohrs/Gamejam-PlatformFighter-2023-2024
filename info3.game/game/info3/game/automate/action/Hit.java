package info3.game.automate.action;

import info3.game.entity.Entity;

public class Hit extends Action{

    @Override
    public void exec(Entity e, String direction) {
        e.hit(direction);
    }
    
    
}
