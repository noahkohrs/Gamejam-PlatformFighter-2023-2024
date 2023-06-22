package info3.game.automate.action;

import info3.game.entity.Entity;

public class Jump extends Action {
    public Jump(){}
    
    public Jump(String Direction){
        super(Direction);
    }

    public Jump(String[] Direction){
        
    }
    @Override
    public void exec(Entity e, String Direction) {
        e.jump(Direction);
    }
    
}
