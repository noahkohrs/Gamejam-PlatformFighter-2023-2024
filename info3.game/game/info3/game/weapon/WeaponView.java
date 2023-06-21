package info3.game.weapon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.Game;
import info3.game.entity.EntityView;
import info3.game.entity.Player;

public class WeaponView extends EntityView {
    private Font font;
    BufferedImage[] images;

    public WeaponView(Weapon weapon) throws IOException {
        super(weapon);
        this.font = new Font("Arial", Font.BOLD, 20);
        images = Player.loadSprite("resources/ammo2.png", 1, 1);
    }

    @Override
    public void paint(Graphics g) {
        int width = ((Weapon) entity).player.lifeBar.getWidth();
        int windowHeight = Game.game.m_canvas.getHeight();
        int windowWidth = Game.game.m_canvas.getWidth();

        int y = windowHeight - 15;

        String ammo = Integer.toString(((Weapon) entity).ammo);
        g.setColor(Color.BLACK);
        g.setFont(font);
        if (entity.team == 1) {
            g.drawString(ammo, width + 30, y);
            g.drawImage(images[0], width, y-20, images[0].getWidth(), images[0].getHeight(), null);
        } else {
            g.drawString(ammo, windowWidth - width - 30, y);
            g.drawImage(images[0], windowWidth - width - 60, y-20, images[0].getWidth(), images[0].getHeight(), null);
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
