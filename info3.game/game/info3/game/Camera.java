package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.jcraft.jogg.Buffer;

import info3.game.entity.Entity;

public class Camera {

    public static boolean debugMode = true;

    void toggleDebugMode() {
        debugMode = !debugMode;
    }

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
        return entity.x + entity.getImage().getWidth() / 2;
    }

    public static int centeredCoordinateY(Entity entity) {
        return entity.y + entity.getImage().getHeight() / 2;
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
        if (debugMode) {
            g.setColor(Color.white);
            g.fillRect(camX, camY, camWidth, camHeight);
        }
    }

    static public void drawImage(Graphics g, BufferedImage img, int x, int y, int width, int height) {
        if (debugMode) {
            g.drawImage(img, x, y, width, height, null);
        } else {
            int cX = onCamViewX(x, camera.scale);
            int cY = onCamViewY(y, camera.scale);
            int cWidth = realSize(width, camera.scale);
            int cHeight = realSize(height, camera.scale);
            g.drawImage(img, cX, cY, cWidth, cHeight, null);
        }

    }

    static public void drawEntity(Entity e, Graphics g) {
        BufferedImage img = e.getImage();
        drawImage(g, img, e.x, e.y, img.getWidth(), img.getHeight());
    }

    static public void drawImage(Graphics g, BufferedImage img, int x, int y, int width, int height, boolean invertedX,
            boolean invertedY) {
        if (invertedX && invertedY) {
            drawImage(g, img, x + width, y + height, -width, -height);
        } else if (invertedX) {
            drawImage(g, img, x + width, y, -width, height);
        } else if (invertedY) {
            drawImage(g, img, x, y + height, width, -height);
        } else {
            drawImage(g, img, x, y, width, height);
        }
    }

    static public void drawEntity(Entity e, Graphics g, boolean invertedX, boolean invertedY) {
        BufferedImage img = e.getImage();
        if (invertedX && invertedY) {
            drawImage(g, img, e.x + img.getWidth(), e.y + img.getHeight(), -img.getWidth(), -img.getHeight());
        } else if (invertedX) {
            drawImage(g, img, e.x + img.getWidth(), e.y, -img.getWidth(), img.getHeight());
        } else if (invertedY) {
            drawImage(g, img, e.x, e.y + img.getHeight(), img.getWidth(), -img.getHeight());
        } else {
            drawImage(g, img, e.x, e.y, img.getWidth(), img.getHeight());
        }
    }

    static public void drawRect(Graphics g, int x, int y, int width, int height) {
        if (debugMode) {
            g.drawRect(x, y, width, height);
        } else {
            int cX = onCamViewX(x, camera.scale);
            int cY = onCamViewY(y, camera.scale);
            int cWidth = realSize(width, camera.scale);
            int cHeight = realSize(height, camera.scale);
            g.drawRect(cX, cY, cWidth, cHeight);
        }

    }

    static public void fillRect(Graphics g, int x, int y, int width, int height) {
        if (debugMode) {
            g.drawRect(x, y, width, height);
        } else {
            int cX = onCamViewX(x, camera.scale);
            int cY = onCamViewY(y, camera.scale);
            int cWidth = realSize(width, camera.scale);
            int cHeight = realSize(height, camera.scale);
            g.fillRect(cX, cY, cWidth, cHeight);
        }

    }

}
