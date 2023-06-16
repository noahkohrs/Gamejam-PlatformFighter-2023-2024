package info3.game.automate.action;

import info3.game.entity.Entity;

public class Cell extends Action{

    Cell(){};

    Cell(String Direction, String Category){
        super(Direction,Category);
    }

    @Override
    public void exec(Entity e, String Direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exec'");
    }
}