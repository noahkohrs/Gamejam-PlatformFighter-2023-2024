package info3.game.automate.action;

import info3.game.entity.Entity;

public class Throw extends Action{

    @Override
    public void exec(Entity e, String Direction) {
        System.out.println("We do not use Throw, so in order to not have a problem with the parser, it always return void");
        return;
    }
    
}
