package info3.game.automate;
import info3.game.automate.action.Action;
import info3.game.automate.condition.Condition;

public class Transitions {
    State src;
    State dest;
    Action action;
    public Condition cond;

    public Transitions(State src,State dest,Action action,Condition cond){
        this.src=src;
        if(src==null || dest==null)
            return;
        if(src.equals(dest))
            this.dest=this.src;
        else
            this.dest=dest;
        this.action=action;
        this.cond=cond;
    }

}
