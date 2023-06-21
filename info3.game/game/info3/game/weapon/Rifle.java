package info3.game.weapon;

import java.io.IOException;

import info3.game.entity.Player;

public class Rifle extends Weapon {

    public Rifle(Player player) throws IOException {
        super(player);
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
