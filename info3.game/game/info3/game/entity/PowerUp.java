package info3.game.entity;

import java.io.IOException;

import info3.game.entity.blocks.PowerUpBlock;


public class PowerUp extends DynamicEntity{
    public String name;
    public PowerUpBlock parent;

    public PowerUp(int x, int y, String filename, int nrows, int ncols, String name, PowerUpBlock parent) throws IOException {
        super(x, y, TEAM.NONE, filename, nrows, ncols);
        this.name = name;
        this.parent = parent;
    }

    
    public void tick(long elapsed){}

    public void move(Direction direction){}

    public void wizz(){}

    public void pop(){}
}
