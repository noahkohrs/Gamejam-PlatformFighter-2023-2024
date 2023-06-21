package info3.game.automate.action;

import info3.game.entity.Entity;

public class Jump extends Action {

    @Override
    public void exec(Entity e, String Direction) {
        e.jump(info3.game.entity.Direction.fromString(Direction));
    }
    
}
