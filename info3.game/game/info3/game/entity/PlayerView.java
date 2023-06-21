package info3.game.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.Camera;
import info3.game.Game;

public class PlayerView extends EntityView {

    int deltatime = 0;
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
            if (((Player) entity).accelerationX == 0.1) {
                imageIndex = (imageIndex + 1) % 2;
            } else {
                imageIndex = (imageIndex + 1) % 4;
            }
        }
    }

    public void paintKills(Graphics g){
        int height = ((Player) this.entity).lifeBar.getHeight();
        int windowHeight = Game.game.m_canvas.getHeight();
        int windowWidth = Game.game.m_canvas.getWidth();

        int y = windowHeight - height;

        String kills = Integer.toString(((Player) entity).kills);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        if (entity.team == 1) {
            g.drawString(kills, 10, y);
        } else {
            g.drawString(kills, windowWidth - 10, y);
        }
    }
    @Override
    public void paint(Graphics g) {
        // Camera.drawImage(g, getImage(), entity.x, entity.y, entity.getWidth(),
        // entity.getHeight());
        entity.hitbox.showHitBox(g);
        ((Player) entity).lifeBar.showLifeBar(g);
        // didnt know a better way to implement it ATM
        Camera.drawEntity(entity, g);
        paintKills(g);
    }
}
