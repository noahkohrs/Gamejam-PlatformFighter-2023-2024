package info3.game.weapon;

import java.io.IOException;

import info3.game.GameSession;

public class Bazooka extends Weapon {


  public Bazooka() throws IOException {
    super(GameSession.gameSession.player2);
    System.out.println("Bazooka called");
    damage = 100;
    ammo = 1;
    clips = 1;
  }

  @Override
  public void createBullet(int startx, int starty) {
    try {
      new Bullet(startx, starty, damage, player.facingDirection, player.team);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

 

}
