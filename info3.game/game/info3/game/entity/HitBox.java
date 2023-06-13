package info3.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.GameSession;

public class HitBox {
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private Entity entity;

    public HitBox(int offsetX, int offsetY, int width, int height, Entity entity) {
        this.height = height;
        this.width = width;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.entity = entity;
    }

    public boolean inCollision(Direction dir) {
        int x, y;
        int maxBoundX = GameSession.gameSession.map.realWidth();
        int maxBoundY = GameSession.gameSession.map.realHeight();
        if (dir.x == 1) {
            x = (entity.x + offsetX + width);
            if (x >= maxBoundX)
                return true;
        } else {
            x = (entity.x + offsetX);
            if (x + width >= maxBoundX)
                return true;
        }
        if (x < 0)
            return true;

        if (dir.y == 1) {
            y = (entity.y + offsetY + height);
            if (y >= maxBoundY)
                return true;
        } else {
            y = (entity.y + offsetY);
            if (y + height >= maxBoundY)
                return true;
        }

        if (y < 0)
            return true;

        int blockX = x / 32;
        int blockY = y / 32;
        int blockHeight = (int) Math.floor(height / 32) + 1;
        int blockWidth = (int) Math.floor(width / 32) + 1;

        switch (dir) {
            case UPPER:
            case BOTTOM: {
                for (int i = 0; i < blockWidth; i += 2) {
                    Block b1 = GameSession.gameSession.map.getBlockWithIndex(blockX + i, blockY);
                    Block b2 = GameSession.gameSession.map.getBlockWithIndex(blockX + i + 1, blockY);
                    // TODO NEED TO CHECK BOUNDARIES
                    if (b2 != null) {
                        if (x + width > b2.x)
                            return true;
                    }
                    if (b1 != null)
                        return true;
                }
                break;
            }
            case LEFT:
            case RIGHT: {
                for (int i = 0; i < blockHeight; i += 2) {
                    Block b1 = GameSession.gameSession.map.getBlockWithIndex(blockX, blockY + i);
                    Block b2 = GameSession.gameSession.map.getBlockWithIndex(blockX, blockY + i + 1);
                    if (b2 != null) {
                        if (y + height > b2.y)
                            return true;
                    }
                    if (b1 != null)
                        return true;
                }
                break;
            }
        }
        return false;
    }

    // Need to check in function of Direction after proto
    private boolean checkMapCollision() {
        return false;
    }

    public void paint(Graphics g) {
        int x = entity.x + offsetX;
        int y = entity.y + offsetY;
        g.setColor(Color.green);
        g.drawRect(x, y, width, height);

    }

}
