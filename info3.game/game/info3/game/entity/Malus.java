package info3.game.entity;

import java.io.IOException;

public class Malus extends DynamicEntity {
    public String name;

    public Malus(int x, int y, String filename, int nrows, int ncols, String name) throws IOException {
        super(x, y, TEAM.NONE, filename, nrows, ncols);
        this.name = name;

    }

    public void tick(long elapsed) {}

    public void move(Direction direction) {}

    public void wizz() {}

    public void pop() {}
}
