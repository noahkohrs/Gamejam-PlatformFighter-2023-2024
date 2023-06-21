package info3.game.weapon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.Game;
import info3.game.entity.EntityView;

public class WeaponView extends EntityView {
    private Font font;

    public WeaponView(Weapon weapon) {
        super(weapon);
        this.font = new Font("Arial", Font.BOLD, 20);
    }

    @Override
    public void paint(Graphics g) {
        if(((Weapon) entity).player.isDead())
            return;
        int width = ((Weapon) entity).player.lifeBar.getWidth();
        int windowHeight = Game.game.m_canvas.getHeight();
        int windowWidth = Game.game.m_canvas.getWidth();

        int y = windowHeight - 15;

        String ammo = Integer.toString(((Weapon) entity).ammo);
        g.setColor(Color.BLACK);
        g.setFont(font);
        if (entity.team == 1) {
            g.drawString(ammo, width + 20, y);
        } else {
            g.drawString(ammo, windowWidth - width - 30, y);
        }
    }

    @Override
    public void tick(long elapsed) {
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

}
