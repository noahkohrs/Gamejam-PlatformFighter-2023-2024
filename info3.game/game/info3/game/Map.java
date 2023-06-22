package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import info3.game.entity.Block;
import info3.game.entity.Entity;
import info3.game.entity.blocks.GrassBlock;
import info3.game.entity.blocks.GroundBlock;
import info3.game.entity.blocks.SpawnerPoint;
import info3.game.entity.blocks.MalusBlock;
import info3.game.entity.blocks.PortalBlock;
import info3.game.entity.blocks.PowerUpBlock;

public class Map {
    private int width;
    private int height;
    // Tab of blocks
    public Block fixedMap[][];
    public BufferedImage background;

    private int indexPowerUp = 0;

    public Map(String filename) throws IOException {
        loadTiles(filename);
    }

    void loadTiles(String filename) throws IOException {
        // Load the map from the file level.json
        // Convert a file to a string
        String content = readFile(filename);
        JSONObject json = new JSONObject(content);

        this.width = json.getInt("width");
        this.height = json.getInt("height");
        fixedMap = new Block[width][height];
        File imageFile;
        try {
            imageFile=new File(json.getString("background"));
        } catch (Exception e) {
            imageFile=new File("resources/backgrounds/BG.png");
        }

        if (!imageFile.exists()) { background=ImageIO.read(new File("resources/backgrounds/BG.png")); } 
        else { background=ImageIO.read(imageFile); }
        


        JSONArray jsonBlocks = json.getJSONArray("blocks");
        for (int i = 0; i < jsonBlocks.length(); i++) {
            JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
            String id = jsonBlock.getString("id");
            int x = jsonBlock.getInt("x");
            int y = jsonBlock.getInt("y");
            Set<String> tags = jsonBlock.getJSONObject("tags").keySet();

            // If it need somes tags...
            fixedMap[x][y] = IdToBlock(id, x, y, tags);
        }
    }


    Block IdToBlock(String id, int x, int y, Set<String> tags) throws IOException {
        switch (id) {
            case "GrassBlock":
                return new GrassBlock(x, y);
            case "SpawnerPoint":
                SpawnerPoint res = new SpawnerPoint(x, y);
                GameSession.gameSession.spawnerPoints.add(res);
                return res;
            case "GroundBlock":
                return new GroundBlock(x, y);
            default:
                throw new IOException("Unknown block id: " + id);
        }
    }

    public int realWidth() {
        return width * 32;
    }

    public int realHeight() {
        return height * 32;
    }

    public Block getBlockWithIndex(int x, int y) {
        if (x < 0)
            return null;
        if (y < 0)
            return null;
        if (x >= width)
            return null;
        if (y >= height)
            return null;
        return this.fixedMap[x][y];
    }

    void paint(Graphics g, Camera camera) {
        Camera.drawImage(g,background,-200,-140,realWidth()+200*2,realHeight()+140*2);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (fixedMap[i][j] != null) {
                    Entity entity = fixedMap[i][j];
                    entity.view.paint(g);
                }
        g.setColor(Color.yellow);
        Camera.drawRect(g, 0, 0, realWidth(), realHeight());

    }

    static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public List<Block> getBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (fixedMap[i][j] != null) {
                    blocks.add(fixedMap[i][j]);
                }
        return blocks;
    }
}
