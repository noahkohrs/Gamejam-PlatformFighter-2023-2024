package info3.game.automate.action;

import info3.game.entity.Entity;

public abstract class Action {
    public String Direction;
    public String Category;

    public Action(){
        this.Direction="NONE";
    }
    
    public Action(String Direction) {
        this.Direction=Direction;
    }

    public Action(String Direction,String Category){
        this.Direction=Direction;
        this.Category=Category;
    }

    public abstract void exec(Entity e,String Direction);
}