package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.automate.Automate;

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

    void kill() {
        GameSession.gameSession.removeEntity(this);
    }

    @Override
    public boolean gotPower() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gotPower'");
    }

    @Override
    public void turn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Turn'");
    }

    
}
