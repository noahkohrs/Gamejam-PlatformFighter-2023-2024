package info3.game.hitbox;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import info3.game.GameSession;
import info3.game.entity.Block;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.blocks.MovingPlatform;

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
            case RIGHT:
            case LEFT_TOP:
            case RIGHT_TOP: {
                for (int i = 0; i < blockHeight; i += 2) {
                    Block b1 = GameSession.gameSession.map.getBlockWithIndex(blockX, blockY + i);
                    Block b2 = GameSession.gameSession.map.getBlockWithIndex(blockX, blockY + i + 1);
                    if (b1 != null)
                        return true;

                    if (b2 != null) {
                        if (y + height > b2.y)
                            return true;
                    }
                }
                break;
            }
        }
        return false;
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

    public  boolean DynamicEntityCollision(Direction dir){//bugged what is needed here is the fact that if upper is called before bottom it should return false
                switch(dir){
                    case UPPER:
                            for(DynamicEntity e: GameSession.gameSession.entities){
                            if(e instanceof MovingPlatform){
                                if(entity.distanceTo(e) < 27){
                                if((e.y < entity.y + entity.getHeight()) && ((e.x < entity.x && e.x+e.getWidth() > entity.x) || ( e.x + e.getWidth() > entity.x && e.x + e.getWidth() >  entity.x + entity.getWidth()))){
                                    return true;
                                }
                            }
                        }
                        }
                    case BOTTOM:
                        for(DynamicEntity e: GameSession.gameSession.entities){
                            if(e instanceof MovingPlatform){
                                if(entity.distanceTo(e) < 27){
                                if((e.y < entity.y + entity.getHeight()) && ((e.x < entity.x && e.x+e.getWidth() > entity.x) || ( e.x + e.getWidth() > entity.x && e.x + e.getWidth() >  entity.x + entity.getWidth()))){
                                    return true;
                                }
                            }
                        }
                        }
                    case LEFT:
                            for(DynamicEntity e: GameSession.gameSession.entities){
                            if(e instanceof MovingPlatform){
                                if(entity.distanceTo(e) < 27){
                                if((e.x + e.getWidth() > entity.x)){
                                    return true;
                                }
                            }
                        }
                        }
                    case RIGHT:
                            for(DynamicEntity e: GameSession.gameSession.entities){
                            if(e instanceof MovingPlatform){
                                if(entity.distanceTo(e) < 20){
                                if(e.x < entity.x){
                                    return true;
                                }
                            }
                        }
                        }

                    default:
                    return false;
                }

        }
}
