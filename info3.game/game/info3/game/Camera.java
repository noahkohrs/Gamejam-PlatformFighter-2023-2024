package info3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Camera {

    public static Camera camera ;
    Game game ;
    int camX, camY ;
    int camWidth, camHeight ;
    double scale ;
    int movingTime ;

    void tick(long elapsed) {
        evalCameraVision();
        scale = (double)Game.game.m_canvas.getWidth() / (double)camWidth ;
    }


    private void evalCameraVision() {
        //TODO : current camera vision
    }
    public Camera(Game game) {
        this.game = game;
        camera = this ;
    }

    public void paint(Graphics g) {
        g.fillRect(camX, camY, camWidth, camHeight);
    }

    static public void drawImage(Graphics g, BufferedImage img, int x, int y, int width, int height) {
        //TODO : draw image
            
        //g.drawImage(img, cX, cY, Viewport.realSize(img.getWidth(), scale), Viewport.realSize(img.getHeight(), scale), null);
    }
    static public void drawRect(Graphics g, int x, int y, int width, int height) {
        //TODO : draw rect
            
    }
    static public void fillRect(Graphics g, int x, int y, int width, int height) {
        //TODO : draw rect
            
    }
        

}
