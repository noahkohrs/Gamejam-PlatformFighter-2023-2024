package info3.game;

import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
    public Map map;

    public GameSession(Game game, String mapPath) throws IOException {
        this.game = game;
        entities = new ArrayList<Entity>();
        map = new Map(mapPath);
        loadEntities(mapPath);
        camera = new Camera();
        gameSession = this;
        player1 = new Player(1);
        player2 = new Player(2);
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
            entities.add(IdToEntity(id, x, y));
        }
    }

    private Entity IdToEntity(String id, int x, int y) {
        switch (id) {
            default:
                return null;
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
        player1.view.paint(g);
        player2.view.paint(g);
    }

    int getLevelWidth() {
        return map.realWidth();
    }

    int getLevelHeight() {
        return map.realHeight();
    }

}
