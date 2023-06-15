package info3.game.automate.action;

import info3.game.entity.Entity;

public class Turn extends Action {

    @Override
    public void exec(Entity e, String Direction) {
        e.turn();
    }
    
}
