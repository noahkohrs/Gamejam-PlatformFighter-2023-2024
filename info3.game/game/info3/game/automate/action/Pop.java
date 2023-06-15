package info3.game.automate.action;

import info3.game.entity.Entity;

public class Pop extends Action{
    public Pop(){}
    
    public Pop(String Direction){
        super(Direction);
    }
    @Override
    public void exec(Entity e, String Direction) {
        e.pop();
    }
}
