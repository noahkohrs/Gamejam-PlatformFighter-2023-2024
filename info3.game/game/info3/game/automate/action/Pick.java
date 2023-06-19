package info3.game.automate.action;

import info3.game.entity.Entity;

public class Pick extends Action{
    public Pick(){}

    public Pick(String Direction){
        super(Direction);
    }
    @Override
    public void exec(Entity e, String Direction) {
        e.pick();
    }
}
