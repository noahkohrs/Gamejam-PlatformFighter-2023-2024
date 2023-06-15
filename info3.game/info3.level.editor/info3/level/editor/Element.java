package info3.level.editor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

import info3.game.entity.Entity;

public abstract class Element {
    public static final int DEFAULT_SIZE = 32;
    BufferedImage[] m_images;
    int m_imageIndex;
    static String spritePath;


    public Element(String filename) throws IOException {
        //Check if file exists
        spritePath = new String(filename);
        loadImage();
    }

    public void loadImage() throws IOException {
        System.out.println("Loading image " + spritePath);
        File f = new File(spritePath);
        if (!spritePath.equals("") && !f.exists()) {
            throw new IOException("File " + spritePath + " does not exist");
        }
        m_images = Entity.loadSprite(spritePath, 1, 1);
        System.out.println("Loaded image " + spritePath);
    }

    public void paint(Graphics g, int x, int y, float scale) {
        if (!spritePath.equals("") && m_images != null && m_images[0] != null) {
            BufferedImage img = m_images[0];
            g.drawImage(img, tileRealSize(scale)*x, tileRealSize(scale)*y, tileRealSize(scale), tileRealSize(scale), null);

        } else {
            g.setColor(java.awt.Color.darkGray);
            g.fillRect(tileRealSize(scale)*x, tileRealSize(scale)*y, tileRealSize(scale)-1, tileRealSize(scale)-1);
        }
    }

    static public int tileRealSize(float scale) {
        return (int) (DEFAULT_SIZE * scale);
    }

    abstract public void setTags(JSONObject tags) ;
    
    abstract public String toString();

}
