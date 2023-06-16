package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Malus;
import info3.game.entity.TEAM;



public class MalusBlock extends DynamicEntity{

    public Malus malus;

    public MalusBlock(int x, int y, int nrows, int ncols) throws IOException{
        super(x, y, TEAM.NONE, "resources/blocks/malusBlock.png", 1, 1);
    }

    @Override
    public void tick(long elapsed) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void move(Direction direction) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void wizz() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'wizz'");
    }

    @Override
    public void pop() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public boolean gotPower(){
        return true;
    }
    
}
