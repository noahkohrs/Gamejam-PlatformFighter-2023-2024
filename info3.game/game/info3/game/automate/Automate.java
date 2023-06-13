package info3.game.automate;

import java.util.ArrayList;
import java.util.List;

import info3.game.entity.Entity;

public class Automate {
    List<Transitions> trans;
    public Entity e;
    State src;
    List<State> states;

    public Automate(){
        trans=new ArrayList<Transitions>();
        e=null;
        src=null;
        states=new ArrayList<State>();
    }
    public Automate(List<Transitions> trans, Entity e,State src,List<State> states) {
        this.trans = trans;
        this.e = e;
        this.src=src;
        this.states=states;
    }

    public void step() throws Exception{
        for(int i=0;i<trans.size();i++){
            if(src.name.equals(trans.get(i).src.name) && trans.get(i).cond!=null && trans.get(i).cond.eval(e)){
                src=trans.get(i).dest;
                String direction=trans.get(i).action.Direction;
                trans.get(i).action.exec(e, direction);
                return;
            }
        }
        System.out.println("Etat qu'on peut pas partir\n");
    }

    public State existe(State state){
        if(states==null){
            states=new ArrayList<State>();
            return state;
        }
        for(int i=0;i<states.size();i++){
            if(state.name.equals(states.get(i).name))
                return null;
        }
        return state;
    }

    public void addState(State state){
        State toAdd=existe(state);
        if(toAdd!=null)
            states.add(toAdd);
    }

    public void prettyPrint(){
        System.out.println("Automate for "+this.e.getClass().getName().toString());
        for(int i=0;i<trans.size();i++){
            if(trans.get(i).dest==null){
                System.out.println("Etat finale sans transition");
                return;
            }
            System.out.println(trans.get(i).src.name+"->"+trans.get(i).dest.name+" |if "+trans.get(i).cond.getClass().getName().toString()+" then "+trans.get(i).action.getClass().getName().toString());
        }
    }
}