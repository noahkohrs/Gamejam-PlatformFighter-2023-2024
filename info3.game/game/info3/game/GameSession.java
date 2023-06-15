package info3.game;

import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import info3.game.automata.ast.AST;
import info3.game.automata.ast.Transition;
import info3.game.automata.parser.AutomataParser;
import info3.game.automate.Automate;
import info3.game.automate.ParserToAutomate;
import info3.game.automate.State;
import info3.game.automate.Transitions;
import info3.game.automate.condition.Key;
import info3.game.automate.condition.True;
import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.Player;
import info3.game.entity.blocks.MovingPlatform;

public class GameSession {
    public Game game;
    public static GameSession gameSession;

    public Player player1;
    public Player player2;

    public Camera camera;

    long testelapsed;

    List<DynamicEntity> entities;
    List<Key> keys;
    public Map map;
    public List<Automate> allAutomates;
    public Automate defaultAutomate;

    public GameSession(Game game, String mapPath, String GalFile) throws Exception {
        this.game = game;
        gameSession = this;

        loadAutomates(GalFile);

        keys = new ArrayList<>();
        loadKeys();

        entities = new ArrayList<DynamicEntity>();
        player1 = new Player(1);
        player2 = new Player(2);
        map = new Map(mapPath);
        loadEntities(mapPath);
        camera = new Camera();

    }

    private void loadKeys() {
        for (Automate current : this.allAutomates) {
            for (Transitions transition : current.trans) {
                if (transition.cond instanceof Key)
                    keys.add((Key) transition.cond);
            }
        }
    }

    private void loadEntities(String filename) throws IOException {
        String content = Map.readFile(filename);
        JSONObject json = new JSONObject(content);
        JSONArray jsonEntities = json.getJSONArray("entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
            JSONObject jsonEntity = jsonEntities.getJSONObject(i);
            String id = jsonEntity.getString("id");
            int x = jsonEntity.getInt("x");
            int y = jsonEntity.getInt("y");
            JSONObject tags = jsonEntity.getJSONObject("tags");
            // If it need somes tags...
            IdToEntity(id, x * Block.BLOCK_SIZE, y * Block.BLOCK_SIZE, tags);
        }
    }

    private DynamicEntity IdToEntity(String id, int x, int y, JSONObject tags) throws IOException {
        switch (id) {
            case "MovingPlatform":
                int moveX = tags.getInt("blockMove");
                int speed = tags.getInt("speed");
                return new MovingPlatform(x, y, moveX * Block.BLOCK_SIZE, speed);
            default:
                return null;
        }
    }

    public void addEntity(Entity entity) {
        this.entities.add(0, entity);
    }
    public void removeEntity(DynamicEntity entity) {
        this.entities.remove(entity);
    }

    public void tick(long elapsed) {
        testelapsed += elapsed;
        if (testelapsed >= 24) {
            player1.tick(testelapsed);
            player2.tick(testelapsed);
            for (DynamicEntity entity : entities) {
                entity.tick(testelapsed);
            }
            camera.tick(testelapsed);
            testelapsed = 0;
        }
    }

    public void paint(Graphics g) {
        camera.paint(g);
        map.paint(g);
        for (Entity entity : entities) {
            entity.view.paint(g);
        }
    }

    int getLevelWidth() {
        return map.realWidth();
    }

    int getLevelHeight() {
        return map.realHeight();
    }

    int findKEy(char letter) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).letter == letter) {
                // System.out.println("Found");
                return i;
            }
        }
        return -1;
    }

    public Automate findAutomate(String className) {
        for (int i = 0; i < this.allAutomates.size(); i++) {
            if (this.allAutomates.get(i).className.equals(className)) {
                // System.out.println("Found");
                return this.allAutomates.get(i);
            }
        }
        return null;
    }

    public void loadAutomates(String GalFile) throws Exception {
        List<Transitions> trans = new ArrayList<Transitions>();
        List<State> states = new ArrayList<State>();
        State state = new State("default");
        states.add(state);
        trans.add(new Transitions(state, state, null, new True()));
        defaultAutomate = new Automate(trans, states, state);

        ParserToAutomate parser = new ParserToAutomate();
        AST ast;
        ast = AutomataParser.from_file(GalFile);
        ast.accept(parser);
        allAutomates = parser.autos;
    }

}
