package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.automate.Automate;
import info3.game.entity.Entity;

public class MovingPlatform extends Entity {

    public MovingPlatform(int x, int y, Automate automate, String filename, int nrows, int ncols) throws IOException {
        super(x, y, automate, filename, nrows, ncols);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void tick(long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void move(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void wizz() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'wizz'");
    }

    @Override
    public void pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

}
