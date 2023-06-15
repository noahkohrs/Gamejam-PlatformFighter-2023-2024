package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.automate.Automate;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Malus;



public class MalusBlock extends DynamicEntity{

    public Malus malus;

    public MalusBlock(int x, int y, Automate automate, String filename, int nrows, int ncols) throws IOException{
        super(x, y, null, filename, 1, 1);
        int malusY = y-32;
        String f = "resources/powerUp/speed.png";
        malus = new Malus(x, malusY, null, f, 1, 1);
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
    
}
