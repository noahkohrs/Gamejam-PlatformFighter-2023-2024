package info3.level.editor;

import java.awt.*;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Level {

    int x = 20 ;
    int y = 20 ;
    private ElementContainer m_elements[][];
    int width;
    int height;
    private float scale = 1.5f; 
    float scaleChange = 0 ; // 0 = no change, 1 = increase, -1 = decrease 



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


    public void paint(Graphics g) {
        Graphics g2 = g.create(x, y, width*Element.tileRealSize(scale)+1, height*Element.tileRealSize(scale)+1);
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
                if (scale < 0.15f) { scale = 0.2f; }
            }
        }
    }

    public int getRealWidth() {
        return width*Element.tileRealSize(scale);
    }

    public int getRealHeight() {
        return height*Element.tileRealSize(scale);
    }

    public ElementContainer select(int x, int y) {
        return m_elements[x/Element.tileRealSize(scale)][y/Element.tileRealSize(scale)];
    }

    public void changeElement(int x, int y) {
        m_elements[x/Element.tileRealSize(scale)][y/Element.tileRealSize(scale)].changeElement(LevelEditor.levelEditor.selected.m_element);
    }



    public void exportJson(String filenameDest) {
        JSONObject levelJson = new JSONObject();
        JSONArray blocks = new JSONArray() ;
        JSONArray animatedEntities = new JSONArray() ;
        for (int i = 0 ; i < m_elements.length ; i++) {
            for (int j = 0 ; j < m_elements[i].length ; j++) {
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
        levelJson.put("background", "<DEFAULT>");
        try {
            java.io.FileWriter fw = new java.io.FileWriter(filenameDest);
            fw.write(levelJson.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
