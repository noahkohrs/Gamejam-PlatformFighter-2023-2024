package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import info3.game.entity.Block;
import info3.game.entity.Entity;
import info3.game.entity.blocks.GrassBlock;

public class Map {
    private int width;
    private int height;
    // Tab of blocks
    Block fixedMap[][];

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
            case "GrassBlock" :
                return new GrassBlock(x, y);
            default :
                return new GrassBlock(x, y);
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
        if (y >= height )
            return null;
        return this.fixedMap[x][y];
    }

    void paint(Graphics g) {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (fixedMap[i][j] != null)
                    fixedMap[i][j].paint(g);
        g.setColor(Color.yellow);
        Camera.drawRect(g, 0, 0, realWidth(), realWidth());
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
}
