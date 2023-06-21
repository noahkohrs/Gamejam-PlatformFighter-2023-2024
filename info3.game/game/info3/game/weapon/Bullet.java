package info3.game.weapon;

import java.io.IOException;
import info3.game.entity.Direction;
import info3.game.hitbox.HitBox;

public class Bullet extends Projectile {

  public Bullet(int x, int y, int damage, Direction dir, int team) throws IOException {
    super(x, y, dir, team, damage, 20, "resources/bullets/1.png", 1, 1);

    this.hitbox = new HitBox(0, 0, 10, 10, true, this);
  }
}
