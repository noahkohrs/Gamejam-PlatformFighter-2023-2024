package info3.game.automate.action;

import info3.game.entity.Entity;

public class Wizz extends Action{
    public Wizz(){}
    
    public Wizz(String Direction){
        super(Direction);
    }
    @Override
    public void exec(Entity e, String Direction) {
        e.wizz(Direction);
    }
}
