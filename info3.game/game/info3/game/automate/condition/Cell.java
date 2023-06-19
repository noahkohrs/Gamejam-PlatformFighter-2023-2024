package info3.game.automate.condition;

import info3.game.entity.Entity;

public class Cell extends Condition {

    String Direction;
    String Category;
    public Cell(boolean notOp) {
        super(notOp);
        //TODO Auto-generated constructor stub
    }

    public Cell(String Direction,String Category){
        super(false);
        this.Category=Category;
        this.Direction=Direction;
    }
    @Override
    public boolean eval(Entity e) {
        return e.cell(info3.game.entity.Direction.fromString(Direction),Category);
    }
    
}
