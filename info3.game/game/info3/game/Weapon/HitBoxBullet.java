package info3.game.Weapon;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.Game;

public class HitBoxBullet {
    int x;
    int y;
    int width;
    int height;
    final int mapCollisionX;
    final int mapCollisionY;
    public boolean collided;

    public HitBoxBullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        // need to check in function of Direction after proto
        Block b = Game.game.m_map.getFirstBlockOnLineX(x, y);
        if (b != null) {
            Block b2 = Game.game.m_map.getFirstBlockOnLineX(x, y + height);

            if (b2 != null) {
                if (b.m_x <= b2.m_x) {
                    mapCollisionX = b.m_x - width;
                    mapCollisionY = b.m_y;
                } else {
                    mapCollisionX = b2.m_x - width;
                    mapCollisionY = b2.m_y;
                }
            } else {
                mapCollisionX = b.m_x - width;
                mapCollisionY = b.m_y;
            }
        } else {
            // need to check extreme
            b = Game.game.m_map.getFirstBlockOnLineX(x, y + height);
            if (b != null) {
                mapCollisionX = b.m_x - width;
                mapCollisionY = b.m_y;
            } else {
                mapCollisionX = Game.game.m_map.realWidth() - width;
                mapCollisionY = Game.game.m_map.realHeight();
            }
        }

    }

    public void checkCollision() {
        checkMapCollision();
        checkPlayerCollision();
    }

    // Need to check in function of Direction after proto
    private void checkMapCollision() {
        if (this.x == mapCollisionX)
            this.collided = true;
        if (this.y == mapCollisionY)
            this.collided = true;
    }

    private void checkPlayerCollision() {

    }

    public void setCoord(int x, int y) {
        this.x = x;
        this.y = y;
        checkCollision();
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(x, y, width, height);

    }

    public void viewportView(Graphics g, double scale) {
        g.setColor(Color.green);
        g.drawRect(Viewport.OnCamViewX(x, scale), Viewport.OnCamViewY(y, scale), Viewport.realSize(width, scale),
                Viewport.realSize(height, scale));

    }
}
