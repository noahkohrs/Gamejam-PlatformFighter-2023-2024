package info3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.Camera;

public class PlayerView extends EntityView {

    long m_imageElapsed = 0;

    PlayerView(BufferedImage[] images, Player player) {
        super(images, player);
    }

    public PlayerView(String string, int nrows, int ncols, Player player) {
        super(string, nrows, ncols, player);
    }

    public void tick(long elapsed) {
        m_imageElapsed += elapsed;
        if (m_imageElapsed > 200) {
            m_imageElapsed = 0;
            imageIndex = (imageIndex + 1) % images.length;
        }
    }

    @Override
    public void paint(Graphics g) {
        Camera.drawImage(g, getImage(), entity.x, entity.y, entity.getWidth(), entity.getHeight());
        entity.hitbox.showHitBox(g);
        ((Player)entity).weapon.showHitBox(g);
        ((Player)entity).lifeBar.showLifeBar(g);
    }

}
