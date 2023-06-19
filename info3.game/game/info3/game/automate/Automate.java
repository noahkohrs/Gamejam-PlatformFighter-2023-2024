package info3.game.automate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import info3.game.automate.action.Action;
import info3.game.automate.condition.Condition;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;

public class Automate {
    public List<Transitions> trans;
    public List<State> states;
    public State initalState;
    public String className;

    public Automate() {
        trans = new ArrayList<Transitions>();
        states = new ArrayList<State>();
    }

    public Automate(List<Transitions> trans, List<State> states, State initialState) {
        this.trans = trans;
        this.states = states;
    }

    public Automate(List<Transitions> trans, List<State> states, State iniState, String className) {
        this.trans = trans;
        this.states = states;
        this.initalState = iniState;
        this.className = className;
    }

    public void step(Entity e) throws Exception {
        Random random = new Random();
        for (int i = 0; i < trans.size(); i++) {
            Transitions transition = trans.get(i);
            State source = transition.src;
            State dest = transition.dest;
            if (source == null || dest == null) {
                System.out.println("Etat without transition");
                return;
            }
            Condition cond = transition.cond;
            Action action = transition.action;
            if (source == e.state && cond.eval(e)) {
                if (action != null) {
                    // else do the action of the transition.
                    String direction = action.Direction;
                    action.exec(e, direction);
                }
                e.state = dest;
                // If we want to go to a random state
                if (dest.name.equals("_")) {
                    int randint = random.nextInt(this.states.size());
                    while (states.get(randint).name.equals("_")) {
                        randint = random.nextInt(this.states.size());
                    }
                    e.state = states.get(randint);
                }
                if (dest.name.equals("")) {
                    ((DynamicEntity) e).kill();
                }
                return;
            }

        }
        //System.out.println("State unescapable\n");
    }

    public State existe(String stateName) {
        // If there are no states, we create the ArrayList and return the stete of the
        // argument
        if (states == null) {
            states = new ArrayList<State>();
            return null;
        }
        // We search if the state already exist. If yes, return null
        for (State state : states) {
            if (stateName.equals(state.name))
                return state;
        }
        // Else return the state of the argument
        return null;
    }

    public State getState(String stateName) {
        State toAdd = existe(stateName);
        if (toAdd == null) {
            toAdd = new State(stateName);
            states.add(toAdd);
        }

        return toAdd;
    }

    public void prettyPrint() {
        // System.out.println("Automate for "+this.e.getClass().getName().toString());
        for (int i = 0; i < trans.size(); i++) {
            if (trans.get(i).dest == null) {
                System.out.println("Etat finale sans transition");
                return;
            }
            System.out.println(trans.get(i).src.name + "->" + trans.get(i).dest.name + " |if "
                    + trans.get(i).cond.getClass().getName().toString() + " then "
                    + trans.get(i).action.getClass().getName().toString());
        }
    }
}