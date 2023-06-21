package info3.game.weapon;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Player;
import info3.game.hitbox.HitBox;

public class Bazooka extends DynamicEntity {

private HitBox hitBox;
  private Direction dir;
  private Player ennemy;
  private int damage;

public Bazooka(int x, int y, int damage, Direction dir, int team)
      throws IOException {
    super(x, y, team, "resources/bullets/1.png", 1, 1);
    hitBox = new HitBox(0, 0, 20, 12, true, this);
    this.dir = dir;
    this.damage = damage;
    if (team == GameSession.gameSession.player1.team)
      ennemy = GameSession.gameSession.player2;
    else
      ennemy = GameSession.gameSession.player1;

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
      int nextX = x + dir.x * 20;
      int nextY = y + dir.y * 20;
      if (hitBox.inPlayerVectorCollision(nextX, nextY, dir)) {
        ennemy.takeDamage(damage);
        kill();
      }
      x = nextX;
      y = nextY;
    } else {
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
