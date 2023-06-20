package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;

public class DynamicEntity extends Entity {

    public DynamicEntity(int x, int y, int team, String filename, int nrows, int ncols) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        GameSession.gameSession.addEntity(this);
    }

    public DynamicEntity(int x, int y, int team) throws IOException {
        super(x, y, team);
        GameSession.gameSession.addEntity(this);
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
    public void wizz(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'wizz'");
    }
    
    @Override
    public void pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public void egg(Entity type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'egg'");
    }

    public void kill() {
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

    @Override
    public boolean cell(Direction direction, String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cell'");
    }

    @Override
    public boolean MyDir(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MyDir'");
    }

    @Override
    public void pick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pick'");
    }
}
