package info3.game.weapon;

import java.io.IOException;

import info3.game.entity.Player;

public class Rifle extends Weapon {

    public Rifle(Player player) throws IOException {
        super(player);
    }

    public Rifle(Player player, int clips, int ammo) throws IOException {
        super(player, clips, ammo);
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
