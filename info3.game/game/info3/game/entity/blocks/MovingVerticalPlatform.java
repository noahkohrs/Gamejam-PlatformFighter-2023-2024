package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.TEAM;
import info3.game.weapon.Bullet;
import info3.game.weapon.Weapon;

public class MovingVerticalPlatform extends DynamicEntity {
    int minY, maxY;
    int power, maxPower;

    public MovingVerticalPlatform(int x, int y, int blockMove, int speed) throws IOException {
        super(x, y, TEAM.NONE, "resources/blocks/movingVerticalPlatform.png", 1, 1);
        maxPower = blockMove;
        power = maxPower;
        solid = true;
        velY = speed;
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
    public void move(Direction direction) {
        switch(direction){
            case UPPER:
              this.y-=10;
              break;
            case BOTTOM:
              this.y+=10;
              break;
            case RIGHT:
              this.x+=10;
              break;
            case LEFT:
              this.x-=10;
              break;
            default:
            y += velY;
                break;
          }
        power -= Math.abs(velY);
        for (DynamicEntity e : GameSession.gameSession.entities) {
            if (e.team != TEAM.NONE && !(e instanceof Weapon) && e.isSittingOn(this) && !(e instanceof Bullet)) {
                e.y += velY;
            }
        }
    }

    @Override
    public boolean gotPower() {
        return power >= 0;
    }

    @Override
    public void turn() {
        velY = -velY;
        power = maxPower;
    }

}