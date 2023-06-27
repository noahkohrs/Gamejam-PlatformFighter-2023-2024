package info3.game.weapon;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.entity.Engineer;
import info3.game.entity.Player;

public class Bazooka extends Weapon {


  public Bazooka(Player player) throws IOException {
    super(player);
    damage = 100;
    ammo = 1;
    clips = 1;
  }

  @Override
  public void createBullet(int startx, int starty) {
    try {
      new Rocket(startx, starty, damage, player.facingDirection, player.team);
      player.weapon = new Rifle(player, ((Engineer) player).oldClips, ((Engineer) player).oldAmmo);
      this.kill();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

 

}
