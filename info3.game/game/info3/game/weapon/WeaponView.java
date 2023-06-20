package info3.game.weapon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.entity.EntityView;

public class WeaponView extends EntityView {

    public WeaponView(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void tick(long elapsed) {
    }
    @Override
    public BufferedImage getImage() {
        return null;
    }

}
