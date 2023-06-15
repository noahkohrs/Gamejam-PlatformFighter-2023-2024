package info3.game.entity;

import java.io.IOException;

public class Block extends Entity {
    public static final int BLOCK_SIZE = 32;

    public Block(int x, int y, String blockTexture) throws IOException {
        super(x*BLOCK_SIZE, y*BLOCK_SIZE, blockTexture, 1, 1);
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
        throw new UnsupportedOperationException("Unimplemented method 'egg'");
    }

    @Override
    public boolean GotPower() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }
}
