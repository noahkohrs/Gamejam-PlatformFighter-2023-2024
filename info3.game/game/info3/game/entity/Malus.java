package info3.game.entity;

import java.io.IOException;

import info3.game.automate.Automate;

public class Malus extends DynamicEntity{

    public Malus(int x, int y, String filename, int nrows, int ncols) throws IOException {
        super(x, y, TEAM.NONE, filename, nrows, ncols);
    }

    public void tick(long elapsed){}

    public void move(Direction direction){}

    public void wizz(){}

    public void pop(){}
}
