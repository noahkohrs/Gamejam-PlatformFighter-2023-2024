package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.Entity;

public class Camera {

    public static Camera camera;
    int camX, camY;
    int camWidth, camHeight;
    double scale;
    int movingTime;
    double screenRatio;

    public Camera() {
        camera = this;
        camWidth = 200;
        camHeight = 200;
        movingTime = 1;
    }

    void tick(long elapsed) {
        screenRatio = (double) Game.game.m_canvas.getWidth() / (double) Game.game.m_canvas.getHeight();
        evalCameraVision();
        scale = (double) Game.game.m_canvas.getWidth() / (double) camWidth;
    }

    private static int onCamViewX(int x, double scale) {
        return (int) ((x - camera.camX) * scale);
    }

    private static int onCamViewY(int y, double scale) {
        return (int) ((y - camera.camY) * scale);
    }

    private static int realSize(int size, double scaleRatioDisplay) {
        return (int) ((double) size * scaleRatioDisplay) + 1;
    }

    public static int centeredCoordinateX(Entity entity) {
        return entity.x + entity.m_images[0].getWidth() / 2;
    }

    public static int centeredCoordinateY(Entity entity) {
        return entity.y + entity.m_images[0].getHeight() / 2;
    }

    private void evalCameraVision() {
        int precCamX = camX;
        int precCamY = camY;
        int precCamWidth = camWidth;
        int precCamHeight = camHeight;

        int p1X = centeredCoordinateX(GameSession.gameSession.player1);
        int p1Y = centeredCoordinateY(GameSession.gameSession.player1);
        int p2X = centeredCoordinateX(GameSession.gameSession.player2);
        int p2Y = centeredCoordinateY(GameSession.gameSession.player2);

        int centerX = (p1X + p2X) / 2;
        int centerY = (p1Y + p2Y) / 2;

        int distX = Math.abs(p1X - p2X);
        int distY = Math.abs(p1Y - p2Y);

        camWidth = distX + 300;
        camHeight = distY + 200;

        if (camWidth < 300)
            camWidth = 300;
        if (camHeight < 200)
            camHeight = 200;

        camX = centerX - camWidth / 2;
        camY = centerY - camHeight / 2;

        if (camX < 0)
            camX = 0;
        if (camY < 0)
            camY = 0;

        // Resize the camera to fit the screen
        if (camWidth < camHeight * screenRatio)
            camWidth = (int) (camHeight * screenRatio);
        else
            camHeight = (int) (camWidth / screenRatio);

        if (camX + camWidth > GameSession.gameSession.map.realWidth())
            camX = GameSession.gameSession.map.realWidth() - camWidth;
        if (camY + camHeight > GameSession.gameSession.map.realHeight())
            camY = GameSession.gameSession.map.realHeight() - camHeight;

        camX = precCamX + (camX - precCamX) * movingTime / 10;
        camY = precCamY + (camY - precCamY) * movingTime / 10;

        camWidth = precCamWidth + (camWidth - precCamWidth) * movingTime / 10;
        camHeight = precCamHeight + (camHeight - precCamHeight) * movingTime / 10;

    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(camX, camY, camWidth, camHeight);
    }

    static public void drawImage(Graphics g, BufferedImage img, int x, int y, int width, int height) {
        int cX = onCamViewX(x, camera.scale);
        int cY = onCamViewY(y, camera.scale);
        int cWidth = realSize(width, camera.scale);
        int cHeight = realSize(height, camera.scale);
        g.drawImage(img, cX, cY, cWidth, cHeight, null);
    }

    static public void drawRect(Graphics g, int x, int y, int width, int height) {
        int cX = onCamViewX(x, camera.scale);
        int cY = onCamViewY(y, camera.scale);
        int cWidth = realSize(width, camera.scale);
        int cHeight = realSize(height, camera.scale);
        g.drawRect(cX, cY, cWidth, cHeight);

    }

    static public void fillRect(Graphics g, int x, int y, int width, int height) {
        int cX = onCamViewX(x, camera.scale);
        int cY = onCamViewY(y, camera.scale);
        int cWidth = realSize(width, camera.scale);
        int cHeight = realSize(height, camera.scale);
        g.fillRect(cX, cY, cWidth, cHeight);

    }


}
