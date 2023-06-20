package info3.game.hitbox;

import java.awt.Graphics;
import info3.game.GameSession;
import info3.game.entity.Block;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;

public class HitBox {
    public final int offsetX;
    public final int offsetY;
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

    public HitBox(Entity e) {
        this.height = e.getHeight();
        this.width = e.getWidth();
        this.offsetX = 0;
        this.offsetY = 0;
        this.entity = e;
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

    public boolean inCollision(Direction dir) {
        int newX = dir.x == 1 ? (entity.x + offsetX + width) : (entity.x + offsetX);
        int newY = dir.y == 1 ? (entity.y + offsetY + height) : (entity.y + offsetY);
        int x = entity.x + offsetX;
        int y = entity.y + offsetY;

        if (mapCollisionEnabled && checkMapCollision(newX, newY, dir)) {
            return true;
        }
        switch (dir) {
            case LEFT:
                return pointColliding(x, y) || pointColliding(x, y + height) || pointColliding(x, y + height / 2);
            case RIGHT:
                return pointColliding(x + width, y) || pointColliding(x + width, y + height) || pointColliding(x + width, y + height / 2);
            case UPPER:
                return pointColliding(x, y) || pointColliding(x + width, y) || pointColliding(x + width / 2, y);
            case BOTTOM:
                return pointColliding(x, y + height) || pointColliding(x + width, y + height) || pointColliding(x + width / 2, y + height);
        }

        return false;
    }

    private boolean pointInHitbox(int x, int y) {
        int topLeftX = entity.x + offsetX;
        int topLeftY = entity.y + offsetY;
        int bottomRightX = topLeftX + width;
        int bottomRightY = topLeftY + height;

        return x >= topLeftX && x <= bottomRightX && y >= topLeftY && y <= bottomRightY;
    }

    private boolean pointColliding(int x, int y) {
        for (DynamicEntity e : GameSession.gameSession.entities) {
            if (e != entity && e.solid && e.hitbox.pointInHitbox(x, y)) {
                return true; // Collision detected with entity
            }
        }

        for (Block b : GameSession.gameSession.map.getBlocks()) {
            if (b.solid && b.hitbox.pointInHitbox(x, y)) {
                return true; // Collision detected with block
            }
        }

        return false; // No collision
    }

    public boolean isSittingOn(Entity e) {
        int x = entity.x + offsetX;
        int y = entity.y + offsetY + height + 1;
        return e.hitbox.pointInHitbox(x+ width/4, y) || e.hitbox.pointInHitbox(x + 3*width/4, y);
    }

    public int getTopLeftY() {
        return entity.y + offsetY;
    }

    public int getTopLeftX() {
        return entity.x + offsetX;
    }

    public int getBottomRightX() {
        return entity.x + offsetX + width;
    }

    public int getBottomRightY() {
        return entity.y + offsetY + height;
    }

    public int getTopLeftX(int x) {
        return x + offsetX;
    }

    public int getTopLeftY(int y) {
        return y + offsetY;
    }

    public int getBottomRightX(int x) {
        return x + offsetX + width;
    }

    public int getBottomRightY(int y) {
        return y + offsetY + height;
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

        return false;
    }

    public boolean inPlayerVectorCollision(int newX, int newY, Direction dir) {
        int playerXLeft, playerYLeft, playerXRight, playerYRight;
        HitBox player1HitBox = GameSession.gameSession.player1.hitbox;
        HitBox player2HitBox = GameSession.gameSession.player2.hitbox;
        if (entity.team == GameSession.gameSession.player2.team) {
            playerXLeft = player1HitBox.getTopLeftX();
            playerYLeft = player1HitBox.getTopLeftY();
            playerXRight = player1HitBox.getBottomRightX();
            playerYRight = player1HitBox.getBottomRightY();
        } else {
            playerXLeft = player2HitBox.getTopLeftX();
            playerYLeft = player2HitBox.getTopLeftY();
            playerXRight = player2HitBox.getBottomRightX();
            playerYRight = player2HitBox.getBottomRightY();
        }
        if(GameSession.gameSession.player1.dead || GameSession.gameSession.player2.dead)
            return false;
        switch (dir) {
            case LEFT_TOP:
                if (getTopLeftX(newX) <= playerXRight && playerXLeft <= getBottomRightX())
                    if (getTopLeftY() <= playerYRight && playerYLeft <= getBottomRightY())
                        return true;
                if (getTopLeftY(newY) <= playerYRight && playerYLeft <= getBottomRightY())
                    if (getTopLeftX() <= playerXRight && playerXLeft <= getBottomRightX())
                        return true;
                break;
            case LEFT:
                if (getTopLeftX(newX) <= playerXRight && playerXLeft <= getBottomRightX())
                    if (getTopLeftY() <= playerYRight && playerYLeft <= getBottomRightY())
                        return true;
                break;
            case RIGHT_TOP:
                if (getTopLeftX() <= playerXRight && playerXLeft <= getBottomRightX(newX))
                    if (getTopLeftY() <= playerYRight && playerYLeft <= getBottomRightY())
                        return true;
                if (getTopLeftY(newY) <= playerYRight && playerYLeft <= getBottomRightY())
                    if (getTopLeftX() <= playerXRight && playerXLeft <= getBottomRightX())
                        return true;
                break;
            case RIGHT:
                if (getTopLeftX() <= playerXRight && playerXLeft <= getBottomRightX(newX))
                    if (getTopLeftY() <= playerYRight && playerYLeft <= getBottomRightY())
                        return true;
                break;
            case UPPER:
                if (getTopLeftY(newY) <= playerYRight && playerYLeft <= getBottomRightY())
                    if (getTopLeftX() <= playerXRight && playerXLeft <= getBottomRightX())
                        return true;
                break;
            case BOTTOM:
                if (getTopLeftY() <= playerYRight && playerYLeft <= getBottomRightY(newY))
                    if (getTopLeftX() <= playerXRight && playerXLeft <= getBottomRightX())
                        return true;
                break;
        }
        return false;
    }

}
