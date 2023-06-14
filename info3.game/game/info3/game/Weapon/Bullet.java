package info3.game.Weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.Camera;
import info3.game.automate.Automate;
import info3.game.entity.Block;
import info3.game.entity.Direction;
import info3.game.entity.Entity;
import info3.game.entity.HitBox;

public class Bullet extends Entity{

  private HitBox hitBox;
  private Direction dir;

  public Bullet(int x, int y, Direction dir)
      throws IOException {
    super(x,y, new Automate(), "resources/blocks/1.png", 1, 1);

    this.dir = dir;
    hitBox = new HitBox(0, 0, Block.BLOCK_SIZE, Block.BLOCK_SIZE, this);
  }

  @Override
  public void tick(long elapsed) {
    view.tick(elapsed);
    if (!hitBox.inCollision(dir)) {
      x += dir.x;
      y += dir.y;
    }
  }

  public void paint(Graphics g) {
    BufferedImage img = getImage();
    Camera.drawImage(g, img, x, y, getWidth(), getHeight());
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
}
