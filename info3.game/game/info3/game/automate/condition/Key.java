package info3.game.automate.condition;

import info3.game.entity.Entity;

public class Key extends Condition{
    public char letter;
    public boolean pressed;
    public Key(String letter){
        this.letter=letter.charAt(0);
        this.pressed=false;
    }
    @Override
    public boolean eval(Entity e) {
        return pressed;
    }
}
