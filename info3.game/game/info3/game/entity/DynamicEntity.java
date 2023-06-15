package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;

public class DynamicEntity extends Entity {

    public DynamicEntity(int x, int y, String filename, int nrows, int ncols) throws IOException {
        super(x, y, filename, nrows, ncols);
        GameSession.gameSession.addEntities(this);
    }

    @Override
    public void tick(long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void move(Direction direction) {
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

    @Override
    public void egg() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public boolean GotPower() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

}
