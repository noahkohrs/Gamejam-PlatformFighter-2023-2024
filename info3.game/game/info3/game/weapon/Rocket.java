package info3.game.weapon;

import java.io.IOException;
import info3.game.entity.Direction;
import info3.game.hitbox.HitBox;

public class Rocket extends Projectile {
    static String filename = "resources/bullets/rocket.png";

    public Rocket(int x, int y, int damage, Direction dir, int team) throws IOException {
        super(x, y, dir, team, damage, 20, filename, 1, 1);

        this.hitbox = new HitBox(0, 0, 25, 10, true, this);
    }

}
