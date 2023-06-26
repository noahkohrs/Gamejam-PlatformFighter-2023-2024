package info3.level.editor;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.sound.sampled.Port;

import org.json.JSONArray;
import org.json.JSONObject;

import info3.game.entity.blocks.WoodBlock;

public class Level {

    int x = 20;
    int y = 20;
    private ElementContainer m_elements[][];
    String background ;
    int width;
    int height;
    private float scale = 1.5f;
    float scaleChange = 0; // 0 = no change, 1 = increase, -1 = decrease

    private String levelFilename;

    public Level() throws IOException {
        this(10, 10);
    }

    public Level(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        m_elements = new ElementContainer[width][height];
        for (int i = 0; i < m_elements.length; i++) {
            for (int j = 0; j < m_elements[i].length; j++) {
                m_elements[i][j] = new ElementContainer(new VoidBlock(), i, j);
            }
        }
    }

    public Level(String levelFilename) throws IOException {
        this.levelFilename = levelFilename;
        File f = new File(levelFilename);
        if (f.exists()) {
            InputStream is = new FileInputStream(f);
            String fullString = new String(is.readAllBytes());
            is.close();
            JSONObject levelJson = new JSONObject(fullString);
            this.width = levelJson.getInt("width");
            this.height = levelJson.getInt("height");
            this.background = levelJson.getString("background");
            m_elements = new ElementContainer[width][height];
            for (int i = 0; i < m_elements.length; i++) {
                for (int j = 0; j < m_elements[i].length; j++) {
                    m_elements[i][j] = new ElementContainer(new VoidBlock(), i, j);
                }
            }

            JSONArray jsonBlocks = levelJson.getJSONArray("blocks");
            for (int i = 0; i < jsonBlocks.length(); i++) {
                JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
                String id = jsonBlock.getString("id");
                int x = jsonBlock.getInt("x");
                int y = jsonBlock.getInt("y");
                JSONObject tags = jsonBlock.getJSONObject("tags");
                m_elements[x][y] = new ElementContainer(idToElement(id, x, y, tags), x, y);
            }

            JSONArray jsonEntities = levelJson.getJSONArray("entities");
            for (int i = 0; i < jsonEntities.length(); i++) {
                JSONObject jsonBlock = jsonEntities.getJSONObject(i);
                String id = jsonBlock.getString("id");
                int x = jsonBlock.getInt("x");
                int y = jsonBlock.getInt("y");
                JSONObject tags = jsonBlock.getJSONObject("tags");
                m_elements[x][y] = new ElementContainer(idToElement(id, x, y, tags), x, y);
            }

        } else {
            this.width = 60;
            this.height = 30;
            m_elements = new ElementContainer[width][height];
            for (int i = 0; i < m_elements.length; i++) {
                for (int j = 0; j < m_elements[i].length; j++) {
                    m_elements[i][j] = new ElementContainer(new VoidBlock(), i, j);
                }
            }
        }
    }

    Element idToElement(String id, int x, int y, JSONObject tags) throws IOException {
        switch (id) {
            case "GrassBlock":
                return new GrassBlock();
            case "SpawnerPoint":
                SpawnerPoint res = new SpawnerPoint();
                return res;
            case "GroundBlock":
                return new GroundBlock();
            case "MovingHorizontalPlatform":
                int speed = tags.getInt("speed");
                int blockMove = tags.getInt("blockMove");
                return new MovingHorizontalPlatform(speed, blockMove);
            case "MovingVerticalPlatform":
                speed = tags.getInt("speed");
                blockMove = tags.getInt("blockMove");
                return new MovingVerticalPlatform(speed, blockMove);
            case "MalusBlock":
                return new MalusBlock();
            case "PowerUpBlock":
                return new PowerUpBlock();
            case "PortalBlock":
                int portal_id = tags.getInt("id");
                return new PortalBlock(portal_id);
            case "WoodBlock" :
                return new woodBlock();
            case "StoneBlock":
                return new StoneBlock();
            case "LeaveBlock":
                return new LeaveBlock();
            default:
                throw new IOException("Unknown block id: " + id);
        }
    }

    public void paint(Graphics g) {
        Graphics g2 = g.create(x, y, width * Element.tileRealSize(scale) + 1, height * Element.tileRealSize(scale) + 1);
        for (int i = 0; i < m_elements.length; i++) {
            for (int j = 0; j < m_elements[i].length; j++) {
                m_elements[i][j].paint(g2, i, j, scale);
            }
        }
        g.setColor(java.awt.Color.black);
    }

    private int scaleUpdate = 0;

    public void tick(long elapsed) {
        if (scaleChange != 0) {
            scaleUpdate += elapsed;
            if (scaleUpdate > 100) {
                scaleUpdate = 0;
                scale *= scaleChange;
                if (scale < 0.15f) {
                    scale = 0.2f;
                }
            }
        }
    }

    public int getRealWidth() {
        return width * Element.tileRealSize(scale);
    }

    public int getRealHeight() {
        return height * Element.tileRealSize(scale);
    }

    public ElementContainer select(int x, int y) {
        return m_elements[x / Element.tileRealSize(scale)][y / Element.tileRealSize(scale)];
    }

    public void changeElement(int x, int y) throws InstantiationException, IllegalAccessException {
        ElementContainer elem = m_elements[x / Element.tileRealSize(scale)][y / Element.tileRealSize(scale)];
        Element currentSelection = LevelEditor.levelEditor.selected.m_element;
        if (!(elem.m_element.getClass().equals(currentSelection.getClass()))) {
            elem.m_element = LevelEditor.levelEditor.selected.m_element.copy();

        }
    }

    public void exportJson() {
        JSONObject levelJson = new JSONObject();
        JSONArray blocks = new JSONArray();
        JSONArray animatedEntities = new JSONArray();
        for (int i = 0; i < m_elements.length; i++) {
            for (int j = 0; j < m_elements[i].length; j++) {
                Element elem = m_elements[i][j].m_element;
                JSONObject json = new JSONObject();
                json.put("x", i);
                json.put("y", j);
                json.put("id", elem instanceof VoidBlock ? "void" : m_elements[i][j].toString());
                JSONObject tags = new JSONObject();
                // element add the tags it wants to export
                elem.setTags(tags);
                json.put("tags", tags);
                if (!(elem instanceof VoidBlock)) {
                    if (elem instanceof Block) {
                        blocks.put(json);
                    } else if (elem instanceof AnimatedEntity) {
                        animatedEntities.put(json);
                    }
                }
            }
        }
        levelJson.put("blocks", blocks);
        levelJson.put("entities", animatedEntities);
        levelJson.put("width", width);
        levelJson.put("height", height);
        if (background == null)
            levelJson.put("background", "<DEFAULT>");
        else
            levelJson.put("background", background);
        try {
            System.out.println(levelFilename);
            java.io.FileWriter fw = new java.io.FileWriter(this.levelFilename);
            fw.write(levelJson.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
