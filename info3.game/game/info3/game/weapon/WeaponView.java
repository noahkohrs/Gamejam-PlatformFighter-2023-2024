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
        images = Player.loadSprite("resources/weaponView.png", 2, 1);
    }

    @Override
    public void paint(Graphics g) {
        if (((Weapon) entity).player.isDead())
            return;
        int width = ((Weapon) entity).player.lifeBar.getWidth();
        int windowHeight = Game.game.m_canvas.getHeight();
        int windowWidth = Game.game.m_canvas.getWidth();

        int y = windowHeight - 15;

        String ammo = Integer.toString(((Weapon) entity).ammo);
        String clips = Integer.toString(((Weapon) entity).clips);
        g.setColor(Color.BLACK);
        g.setFont(font);
        if (entity.team == 1) {
            g.drawString(ammo, width + 30, y);
            g.drawImage(images[1], width, y - 20, images[1].getWidth(), images[1].getHeight(), null);
            g.drawImage(images[0], width + images[0].getWidth() + 30, y - 20, images[0].getWidth(),
                    images[0].getHeight(),
                    null);
            g.drawString(clips, width + 60 + images[0].getWidth(), y);
        } else {
            g.drawString(ammo, windowWidth - width - 30, y);
            g.drawImage(images[1], windowWidth - width - 60, y - 20, images[1].getWidth(), images[1].getHeight(), null);
            g.drawImage(images[0], windowWidth - width - 80 - images[0].getWidth() , y - 20, images[0].getWidth(),
                    images[0].getHeight(),
                    null);
            g.drawString(clips, windowWidth - width - 50 - images[0].getWidth(), y);
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
