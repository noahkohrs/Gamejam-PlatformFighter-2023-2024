package info3.game.weapon;

import java.awt.Graphics;
import java.io.IOException;
import info3.game.entity.Block;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.hitbox.HitBox;

public class Bullet extends DynamicEntity {

  private HitBox hitBox;
  private Direction dir;

  public Bullet(int x, int y, Direction dir, int team)
      throws IOException {
    super(x-5, y+25, team, "resources/bullets/1.png", 1, 1);
    hitBox = new HitBox(0, 0, 10, 10, true, this);
    this.dir = dir;
  }

  public void showHitBox(Graphics g)
  {
    hitBox.showHitBox(g);
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
    if (!hitBox.inCollision(dir)) {
      int nextX = x + dir.x*70;
      int nextY = y + dir.y*70;
      if(hitBox.inPlayerVectorCollision(nextX,nextY, dir)){
        System.out.println("Touched player");
        kill();
      }
      x = nextX;
      y = nextY;
    }
    else{
      kill();
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
}
