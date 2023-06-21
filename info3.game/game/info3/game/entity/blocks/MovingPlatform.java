package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.TEAM;

public class MovingPlatform extends DynamicEntity {
    int velX;
    int minX, maxX;
    int power, maxPower;

    public MovingPlatform(int x, int y, int blockMove, int speed) throws IOException {
        super(x, y, TEAM.NONE, "resources/blocks/obsidian.png", 1, 1);
        maxPower = blockMove;
        power = maxPower;
        solid = true;
        velX = speed;
    }

    @Override
    public void tick(long elapsed) {
        try {
            automate.step(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        x += velX;
        power -= Math.abs(velX);
        if (GameSession.gameSession.player1.isSittingOn(this)) {
            GameSession.gameSession.player1.x += velX;
        }
        if (GameSession.gameSession.player2.isSittingOn(this)) {
            GameSession.gameSession.player2.x += velX;
        }
    }

    @Override
    public boolean gotPower() {
        return power >= 0;
    }

    @Override
    public void turn() {
        velX = -velX;
        power = maxPower;
    }

}
