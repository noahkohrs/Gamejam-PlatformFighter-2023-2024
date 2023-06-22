package info3.game.entity;

import java.io.IOException; 

public class Block extends Entity {
    public static final int BLOCK_SIZE = 32;

    public Block(int x, int y, String blockTexture) throws IOException {
        super(x*BLOCK_SIZE, y*BLOCK_SIZE, TEAM.NONE, blockTexture, 1, 1);
        solid = true;
    }

    @Override
    public void tick(long elapsed) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'tick'");
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
    public void egg(Entity type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'egg'");
    }

    @Override
    public boolean MyDir(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MyDir'");
    }

    @Override
    public void pick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'egg'");
    }

    @Override
    public void wizz(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'wizz'");
    }

    @Override
    public boolean gotStuff() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gotStuff'");
    }

    @Override
    public void jump(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jump'");
    }
}
