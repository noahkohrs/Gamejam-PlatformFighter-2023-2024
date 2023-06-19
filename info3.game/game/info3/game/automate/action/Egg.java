package info3.game.automate.action;

import info3.game.entity.Entity;

public class Egg extends Action {

    public Egg(){}

    @Override
    public void exec(Entity e, String PowerUp) {
        e.egg(e);
    }
}
