package info3.game.Weapon;

import java.io.IOException;
import info3.game.entity.Block;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.hitbox.HitBox;

public class Bullet extends DynamicEntity {

  private HitBox hitBox;
  private Direction dir;

  public Bullet(int x, int y, Direction dir)
      throws IOException {
    super(x, y, "resources/blocks/1.png", 1, 1);
    hitBox = new HitBox(0, 0, Block.BLOCK_SIZE, Block.BLOCK_SIZE, true, this);
    this.dir = dir;
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
      x += dir.x*8;
      y += dir.y*8;
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
