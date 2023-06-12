package info3.game;

import java.awt.Graphics;

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
}
