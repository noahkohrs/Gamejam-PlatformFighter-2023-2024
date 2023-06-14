package info3.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.GameSession;

public class HitBox {
    private final int offsetX;
    private final int offsetY;
    private Entity entity;

    public final int width;
    public final int height;

    private final HitBoxView view;

    private boolean mapCollisionEnabled;

    public HitBox(int offsetX, int offsetY, int width, int height, Entity entity) {
        this.height = height;
        this.width = width;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.entity = entity;
        view = new HitBoxView(this);
        mapCollisionEnabled = false;
    }

    public HitBox(int offsetX, int offsetY, int width, int height, boolean mapCollisionEnabled, Entity entity) {
        this.height = height;
        this.width = width;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.entity = entity;
        view = new HitBoxView(this);
        this.mapCollisionEnabled = mapCollisionEnabled;
    }

    public void showHitBox(Graphics g) {
        view.paint(g);
    }

    public int getTopX() {
        return entity.x + offsetX;
    }

    public int getTopY() {
        {
            return entity.y + offsetY;
        }
    }

    public boolean inCollision(Direction dir) {
        int x, y;
        if (dir.x == 1)
            x = (entity.x + offsetX + width);
        else
            x = (entity.x + offsetX);

        if (dir.y == 1)
            y = (entity.y + offsetY + height);
        else
            y = (entity.y + offsetY);

        if (mapCollisionEnabled)
            if (checkMapCollision(x, y, dir))
                return true;

        int blockX = x / Block.BLOCK_SIZE;
        int blockY = y / Block.BLOCK_SIZE;
        int blockHeight = (int) Math.floor(height / Block.BLOCK_SIZE) + 1;
        int blockWidth = (int) Math.floor(width / Block.BLOCK_SIZE) + 1;

        switch (dir) {
            case UPPER:
            case BOTTOM: {
                for (int i = 0; i < blockWidth; i += 2) {
                    Block b1 = GameSession.gameSession.map.getBlockWithIndex(blockX + i, blockY);
                    Block b2 = GameSession.gameSession.map.getBlockWithIndex(blockX + i + 1, blockY);
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
    private boolean checkMapCollision(int x, int y, Direction dir) {
        if (x < 0)
            return true;

        if (y < 0)
            return true;

        int maxBoundX = GameSession.gameSession.map.realWidth();
        int maxBoundY = GameSession.gameSession.map.realHeight();
        if (x >= maxBoundX)
            return true;
        if (y >= maxBoundY)
            return true;

        switch (dir) {
            case LEFT:
            case UPPER:
                if (x + width >= maxBoundX)
                    return true;
                break;
            case RIGHT:
            case BOTTOM:
                if (y + height >= maxBoundY)
                    return true;
        }
        /*
         * 
         * if (x + width >= maxBoundX)
         * return true;
         * 
         * if (y + height >= maxBoundY)
         * return true;
         */

        return false;
    }

    public void paint(Graphics g) {
        int x = entity.x + offsetX;
        int y = entity.y + offsetY;
        g.setColor(Color.green);
        g.drawRect(x, y, width, height);

    }

}
