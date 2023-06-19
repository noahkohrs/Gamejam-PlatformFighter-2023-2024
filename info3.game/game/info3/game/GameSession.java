package info3.game;

import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import info3.game.automata.ast.AST;
import info3.game.automata.ast.BinaryOp;
import info3.game.automata.parser.AutomataParser;
import info3.game.automate.Automate;
import info3.game.automate.ParserToAutomate;
import info3.game.automate.State;
import info3.game.automate.Transitions;
import info3.game.automate.condition.Key;
import info3.game.automate.condition.Binary;
import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.automate.condition.True;
import info3.game.entity.Entity;
import info3.game.entity.Player;
import info3.game.entity.TEAM;
import info3.game.entity.blocks.MalusBlock;
import info3.game.entity.blocks.MovingPlatform;
import info3.game.entity.blocks.PowerUpBlock;



public class GameSession {
    public Game game;
    public static GameSession gameSession;

    private long updateTime;

    public Player player1;
    public Player player2;

    public Camera camera;

    long testelapsed;

    List<DynamicEntity> entities;
    List<DynamicEntity> toAddEntities;
    List<DynamicEntity> toRemoveEntities;

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
        toAddEntities = new ArrayList<DynamicEntity>();
        toRemoveEntities = new ArrayList<DynamicEntity>();
        player1 = new Player(TEAM.BLUE);
        player2 = new Player(TEAM.RED);
        map = new Map(mapPath);
        loadEntities(mapPath);
        camera = new Camera();
    }

    private void loadKeys() {
        for (Automate current : this.allAutomates) {
            for (Transitions transition : current.trans) {
                if (transition.cond instanceof Key){
                    if(findKEy(((Key)transition.cond).letter)==-1)
                        keys.add((Key) transition.cond);
                }
                else if(transition.cond instanceof Binary){
                    keys.addAll(((Binary)transition.cond).loadKeys());
                }
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
            IdToEntity(id, x*Block.BLOCK_SIZE, y*Block.BLOCK_SIZE, tags);
        }
    }

    private DynamicEntity IdToEntity(String id, int x, int y, JSONObject tags) throws IOException {
        switch (id) {
            case "MovingPlatform":
                int moveX = tags.getInt("blockMove");
                int speed = tags.getInt("speed");
                return new MovingPlatform(x, y, moveX * Block.BLOCK_SIZE, speed);
            case "PowerUpBlock" :
                return new PowerUpBlock(x, y, 1, 1);
            case "MalusBlock" :
                return new MalusBlock(x, y, 1, 1);
            default:
                return null;
        }
    }

    public void addEntity(DynamicEntity entity) {
        this.toAddEntities.add(0, entity);
    }

    public void removeEntity(DynamicEntity entity) {
        this.toRemoveEntities.add(0, entity);
    }

    public void tick(long elapsed) {
        testelapsed += elapsed;
        Iterator<DynamicEntity> removeIterator = toRemoveEntities.iterator();
        while (removeIterator.hasNext()) {
            DynamicEntity entity = removeIterator.next();
            entities.remove(entity);
            removeIterator.remove();
        }
        if (testelapsed >= 24) {
            for (DynamicEntity entity : entities) {
                entity.tick(testelapsed);
            }
            camera.tick(testelapsed);
            testelapsed = 0;
        }
        Iterator<DynamicEntity> addIterator = toAddEntities.iterator();
        while (addIterator.hasNext()) {
            DynamicEntity entity = addIterator.next();
            entities.add(entity);
            addIterator.remove();
        }

    }

    public void paint(Graphics g) {
        boolean Opti = true;
        camera.paint(g);
        map.paint(g, camera, Opti);
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

    public int findKEy(char letter) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).letter == letter) {
                // System.out.println("Found");
                return i;
            }
        }
        return -1;
    }

    public Automate findAutomate(Entity entity) {
        String className = entity.getClass().getSimpleName();
        for (Automate automate : this.allAutomates) {
            if (automate.className.equals(className)) {
                // System.out.println("Found");
                return automate;
            } else if (entity instanceof Player && automate.className.startsWith("Player")) {
                if (automate.className.endsWith("1") && entity.team == TEAM.BLUE) {
                    return automate;
                } else if (automate.className.endsWith("2") && entity.team == TEAM.RED) {
                    return automate;
                }
            }

        }
        return defaultAutomate;
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
