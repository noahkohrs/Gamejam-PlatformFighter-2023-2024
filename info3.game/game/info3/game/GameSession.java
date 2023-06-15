package info3.game;

import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.automate.ParserToAutomate;
import info3.game.automate.condition.Key;
import info3.game.entity.Block;
import info3.game.entity.Entity;
import info3.game.entity.Player;

public class GameSession {
    public Game game;
    public static GameSession gameSession;

    private long updateTime;

    public Player player1;
    public Player player2;

    public Camera camera;

    List<Entity> entities;
    List<Key> keys;
    public Map map;

    public GameSession(Game game, String mapPath, String GalFile) throws Exception {
        this.game = game;
        entities = new ArrayList<Entity>();
        map = new Map(mapPath);
        loadEntities(mapPath);
        camera = new Camera();
        gameSession = this;
        player1=new Player(1);
        player2=new Player(2);
        ParserToAutomate parser= new ParserToAutomate();
        AST ast;
        ast=AutomataParser.from_file(GalFile);
        ast.accept(parser);
        parser.autos.get(0).e=player1;
        parser.autos.get(1).e=player2;
        player1.automate=parser.autos.get(0);
        player2.automate=parser.autos.get(1);
        
        keys=new ArrayList<>();
        keys.add((Key) parser.autos.get(0).trans.get(0).cond);
        keys.add((Key) parser.autos.get(0).trans.get(1).cond);
        keys.add((Key) parser.autos.get(0).trans.get(2).cond);
        keys.add((Key) parser.autos.get(0).trans.get(3).cond);
        keys.add((Key) parser.autos.get(0).trans.get(4).cond);
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
            entities.add(IdToEntity(id, x*Block.BLOCK_SIZE, y*Block.BLOCK_SIZE, tags));
        }
    }

    private Entity IdToEntity(String id, int x, int y, JSONObject tags) throws IOException {
        switch (id) {
            case "MovingPlatform" :
                int moveX = tags.getInt("blockMove");
                int speed = tags.getInt("speed");
                return new MovingPlatform(x, y, moveX*Block.BLOCK_SIZE, speed);
            default :
                return null ;
        }
    }

    public void addEntities(Entity entity)
    {
        this.entities.add(0, entity);
    }

    public void tick(long elapsed) {
        updateTime += elapsed;
        if (updateTime > 24) {
            player1.tick(elapsed);
            player2.tick(elapsed);
            for (Entity entity : entities) {
                entity.tick(elapsed);
            }
            camera.tick(elapsed);
            updateTime=0;
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


    int findKEy(char letter){
        for(int i=0;i<this.keys.size();i++){
            if(this.keys.get(i).letter==letter)
            {
                System.out.println("Found");    
                return i;
            }
        }
        return -1;
    }

}
