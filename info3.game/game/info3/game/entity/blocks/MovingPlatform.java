package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.automate.Automate;
import info3.game.entity.Block;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;

public class MovingPlatform extends DynamicEntity {
    int velX ;
    int minX, maxX;
    int power, maxPower ;


    public MovingPlatform(int x, int y, int blockMove, int speed) throws IOException {
        super(x, y, "resources/blocks/2.png", 1, 1);
        maxPower = blockMove ;
        power = maxPower ;
        
        velX = speed;
    }

    @Override
    public void tick(long elapsed) {
        if (power <= 0) {
            velX = -velX;
            power = maxPower;
        }
        power -= Math.abs(velX);

        x += velX;
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
    public void move(Direction direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
