package info3.game.automate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.ast.State;
import info3.game.automata.ast.Action;
import info3.game.automata.ast.Automaton;
import info3.game.automata.ast.Behaviour;
import info3.game.automata.ast.BinaryOp;
import info3.game.automata.ast.Category;
import info3.game.automata.ast.Condition;
import info3.game.automata.ast.Direction;
import info3.game.automata.ast.FunCall;
import info3.game.automata.ast.IVisitor;
import info3.game.automata.ast.Mode;
import info3.game.automata.ast.Transition;
import info3.game.automata.ast.UnaryOp;
import info3.game.automata.ast.Underscore;
import info3.game.automata.ast.Value;
import info3.game.entity.Entity;

public class ParserToAutomate implements IVisitor {

    public List<Automate> autos;

    public ParserToAutomate() {
        this.autos = new ArrayList<Automate>();
    }

    @Override
    public Object visit(Category cat) {
        // System.out.println("1 "+cat.toString());
        return null;
    }

    @Override
    public Object visit(Direction dir) {
        // System.out.println("2 "+dir.toString());
        return null;
    }

    @Override
    public Object visit(info3.game.automata.ast.Key key) {
        System.out.println("3 " + key.toString());
        return null;
    }

    @Override
    public Object visit(Value v) {
        // System.out.println("4 "+v.toString());
        return null;
    }

    @Override
    public Object visit(Underscore u) {
        // System.out.println("5 "+u.toString());
        return null;
    }

    @Override
    public void enter(FunCall funcall) {
        // System.out.println("6 "+funcall.name);
    }

    @Override
    public Object exit(FunCall funcall, List<Object> parameters) {
        return null;
    }

    @Override
    public Object visit(BinaryOp operator, Object left, Object right) {
        // System.out.println("8 "+left.toString()+" "+operator.toString()+"
        // "+right.toString());
        return null;
    }

    @Override
    public Object visit(UnaryOp operator, Object expression) {
        // System.out.println("9 "+operator.toString()+" "+expression.toString());
        return null;
    }

    @Override
    public Object visit(State state) {
        String statename = state.name;
        info3.game.automate.State stateRes;
        info3.game.automate.Automate currentAutomate = autos.get(autos.size() - 1);
        info3.game.automate.State existe=currentAutomate.existe(state.name);
        stateRes = currentAutomate.getState(state.name);
        //If there is no states
        if (currentAutomate.initalState == null)
            currentAutomate.initalState = stateRes;

        //If there are not transitions, just exit.
        if (currentAutomate.trans == null)
            return null;
        Transitions tr = currentAutomate.trans.get(currentAutomate.trans.size() - 1);
        //If the transition does not have a src. 
        if (tr.src == null) {
            tr.src = stateRes;
        }
        //else if the transition does not have a dest
        else if (tr.dest == null){
            tr.dest = stateRes;
        }
        return null;
    }

    @Override
    public void enter(Mode mode) {
        // System.out.println(mode.toString());
        int index = 0;
        if (autos.get(autos.size() - 1).trans == null) {
            autos.get(autos.size() - 1).trans = new ArrayList<Transitions>();
        } else
            index = autos.get(autos.size() - 1).trans.size();
        autos.get(autos.size() - 1).trans.add(index, new Transitions(null, null, null, null));
        return;
    }

    @Override
    public Object exit(Mode mode, Object source_state, Object behaviour) {
        // System.out.println("12 "+mode.toString());
        return null;
    }

    @Override
    public Object visit(Behaviour behaviour, List<Object> transitions) {
        // System.out.println("13 "+behaviour.toString());
        return null;
    }

    @Override
    public void enter(Condition condition) {
        String className = "info3.game.automate.condition." + condition.expression.name().toString();
        // Load the class dynamically
        Class<?> condClass = null;
        try {
            condClass = Class.forName(className);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        // Create an instance of the condition class
        Object condInstance = null;
        try {
            if (((FunCall) condition.expression).parameters.size() == 0)
                condInstance = condClass.getDeclaredConstructor().newInstance();
            else if (((FunCall) condition.expression).parameters.size() == 1)
                condInstance = condClass.getDeclaredConstructor(String.class)
                        .newInstance(((FunCall) condition.expression).parameters.get(0).toString());
            else if (((FunCall) condition.expression).parameters.size() == 2) {
                String param1 = ((FunCall) condition.expression).parameters.get(0).toString();
                String param2 = ((FunCall) condition.expression).parameters.get(1).toString();
                condInstance = condClass.getDeclaredConstructor(String.class, String.class).newInstance(param1, param2);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {

            e.printStackTrace();
        }
        if (autos.get(autos.size() - 1).trans.get(autos.get(autos.size() - 1).trans.size() - 1).cond != null) {
            Automate a = autos.get(autos.size() - 1);
            a.trans.add(a.trans.size(), new Transitions(autos.get(autos.size() - 1).initalState, null, null, null));
        }
        autos.get(autos.size() - 1).trans.get(autos.get(autos.size() - 1).trans.size()
                - 1).cond = (info3.game.automate.condition.Condition) condInstance;
    }

    @Override
    public Object exit(Condition condition, Object expression) {
        // System.out.println("15 "+(Integer)expression);
        return null;
    }

    @Override
    public void enter(Action action) {
        if (action.calls == null || action.calls.size() == 0)
            return;
        String className = "info3.game.automate.action." + action.calls.get(0).name;
        // System.out.println(className);
        // Load the class dynamically
        Class<?> actionClass = null;
        try {
            actionClass = Class.forName(className);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        // String param=action.calls.get(0).parameters.toString();
        // Create an instance of the action class
        Object actionInstance = null;
        try {
            if (action.calls.get(0).parameters.size() == 0)
                actionInstance = actionClass.getDeclaredConstructor().newInstance();
            else if (action.calls.get(0).parameters.size() == 1) {
                String param = ((FunCall) action.calls.get(0)).parameters.get(0).toString();
                actionInstance = actionClass.getDeclaredConstructor(String.class).newInstance(param);
            } else if (action.calls.get(0).parameters.size() == 2) {
                String param1 = ((FunCall) action.calls.get(0)).parameters.get(0).toString();
                String param2 = ((FunCall) action.calls.get(0)).parameters.get(1).toString();
                actionInstance = actionClass.getDeclaredConstructor(String.class, String.class).newInstance(param1,
                        param2);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {

            e.printStackTrace();
        }
        autos.get(autos.size() - 1).trans.get(autos.get(autos.size() - 1).trans.size()
                - 1).action = (info3.game.automate.action.Action) actionInstance;
    }

    @Override
    public Object exit(Action action, List<Object> funcalls) {
        // System.out.println("17 "+action.toString());
        return null;
    }

    @Override
    public Object visit(Transition transition, Object condition, Object action, Object target_state) {
        // System.out.println("18");
        return null;
    }

    @Override
    public void enter(Automaton automaton) {
        // String add = "";
        // if (automaton.toString().contains("0")) {
        //     add = add + automaton.toString().split("0")[0] + ".";
        //     add = add + automaton.toString().split("0")[1];
        // } else
        //     add = add + automaton.toString();
        String className =  automaton.toString();

        // // Load the class dynamically
        // Class<?> entityClass = null;
        // try {
        //     entityClass = Class.forName(className);
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // }

        // Create an instance of the entity class
        // Object entityInstance=null;
        // try {
        // entityInstance = entityClass.getDeclaredConstructor().newInstance();
        // } catch (InstantiationException | IllegalAccessException |
        // IllegalArgumentException | InvocationTargetException
        // | NoSuchMethodException | SecurityException e) {
        // e.printStackTrace();
        // }
        Automate auto = new Automate(null, null,null,className);
        // auto.e.automate=auto;
        autos.add(autos.size(), auto);
    }

    @Override
    public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
        // System.out.println("20 "+automaton.toString());
        return null;
    }

    @Override
    public Object visit(AST bot, List<Object> automata) {
        // System.out.println("21 "+bot.toString());
        return null;
    }

}
