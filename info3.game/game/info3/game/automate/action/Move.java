package info3.game.automate.action;

import info3.game.entity.Entity;

public class Move extends Action{

    public Move(){}
    
    public Move(String Direction){
        super(Direction);
    }
    @Override
    public void exec(Entity e, String Direction) {
        e.move(info3.game.entity.Direction.fromString(Direction));
    }
}
