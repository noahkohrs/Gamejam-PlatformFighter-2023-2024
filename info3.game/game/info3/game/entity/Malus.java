package info3.game.entity;

import java.io.IOException;

import info3.game.entity.blocks.MalusBlock;

public class Malus extends DynamicEntity {
    public String name;
    MalusBlock parent;

    public Malus(int x, int y, String filename, int nrows, int ncols, String name, MalusBlock parent) throws IOException {
        super(x, y, TEAM.NONE, filename, nrows, ncols);
        this.name = name;
        this.parent = parent;
    }

    public void tick(long elapsed) {}

    public void move(Direction direction) {}

    public void wizz() {}

    public void pop() {}
}
