package info3.game.automate.action;

import info3.game.entity.Entity;

public abstract class Action {
    public String Direction;

    public Action(){
        this.Direction="NONE";
    }
    
    public Action(String Direction) {
        this.Direction=Direction;
    }
    public abstract void exec(Entity e,String Direction);
}