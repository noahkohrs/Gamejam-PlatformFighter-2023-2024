package info3.game.automate.condition;

import info3.game.GameSession;
import info3.game.entity.Entity;

public class Key extends Condition{
    public String name;
    public boolean pressed;
    public Key(String letter, boolean notOp){
        super(notOp);
        this.name=letter;
        this.pressed=false;
    }
    public Key(String letter){
        this(letter, false);
    }
    @Override
    public boolean eval(Entity e) {
        int index = GameSession.gameSession.findKEy(name);
        Key key = GameSession.gameSession.keys.get(index);
        if (key == this) {
            return affectNotOp(pressed);
        } else {
            return affectNotOp(key.getPressed());
        }
        
    }
    public boolean getPressed() {
        return pressed;
    }
}
