package info3.game.automate.condition;

import info3.game.entity.Entity;

public class GotPower extends Condition {
   public boolean eval(Entity e){
    return e.GotPower();
   }

}