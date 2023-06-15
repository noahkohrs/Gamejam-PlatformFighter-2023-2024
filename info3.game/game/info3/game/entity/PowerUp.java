package info3.game.entity;

import java.io.IOException;

import info3.game.automate.Automate;

public class PowerUp extends DynamicEntity{

    public PowerUp(int x, int y, Automate automate, String filename, int nrows, int ncols) throws IOException {
        super(x, y, automate, filename, nrows, ncols);
    }

    public void tick(long elapsed){}

    public void move(Direction direction){}

    public void wizz(){}

    public void pop(){}
}
