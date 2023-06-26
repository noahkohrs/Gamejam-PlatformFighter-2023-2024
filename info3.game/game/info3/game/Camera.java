package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import info3.game.entity.Entity;
import info3.level.editor.Level;

public class Camera {

    public static boolean debugMode = false;
    private static boolean Opti = true;

    void toggleDebugMode() {
        // debugMode = !debugMode;
    }

    public static Camera camera;
    double camX, camY;
    double camWidth, camHeight;
    double scale;
    int movingTime;
    double screenRatio;

    public Camera() {
        camera = this;
        camWidth = 200;
        camX = GameSession.gameSession.map.realWidth() / 2 - 100;
        camY = GameSession.gameSession.map.realHeight() / 2 - 100;
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
        double precCamX = camX;
        double precCamY = camY;
        double precCamWidth = camWidth;
        double precCamHeight = camHeight;

        int p1X = centeredCoordinateX(GameSession.gameSession.player1);
        int p1Y = centeredCoordinateY(GameSession.gameSession.player1);
        int p2X = centeredCoordinateX(GameSession.gameSession.player2);
        int p2Y = centeredCoordinateY(GameSession.gameSession.player2);

        int centerX = (p1X + p2X) / 2;
        int centerY = (p1Y + p2Y) / 2;

        int distX = Math.abs(p1X - p2X);
        int distY = Math.abs(p1Y - p2Y);

        camWidth = distX + 400;
        camHeight = distY + 280;

        if (camWidth < 500)
            camWidth = 500;
        if (camHeight < 350)
            camHeight = 350;

        if (camWidth < camHeight * screenRatio)
            camWidth = (int) (camHeight * screenRatio);
        else
            camHeight = (int) (camWidth / screenRatio);
        // Resize the camera to fit the screen
        if (camX + camWidth > GameSession.gameSession.map.realWidth())
            camX = GameSession.gameSession.map.realWidth() - camWidth;
        if (camY + camHeight > GameSession.gameSession.map.realHeight())
            camY = GameSession.gameSession.map.realHeight() - camHeight;

        camX = centerX - camWidth / 2;
        camY = centerY - camHeight / 2;

        if (GameSession.gameSession.map.realWidth() > screenRatio * GameSession.gameSession.map.realHeight()
                && camWidth > GameSession.gameSession.map.realWidth()) {
            camWidth = GameSession.gameSession.map.realWidth();
            camHeight =(camWidth / screenRatio);
            camX = 0;
            camY = (GameSession.gameSession.map.realHeight() / 2 - camHeight / 2);
        }

        else if (GameSession.gameSession.map.realWidth() < screenRatio * GameSession.gameSession.map.realHeight()
                && camHeight > GameSession.gameSession.map.realHeight()) {
            camHeight = GameSession.gameSession.map.realHeight();
            camWidth =(camHeight * screenRatio);
            camY = 0;
            camX = (GameSession.gameSession.map.realWidth() / 2 - camWidth / 2);
        } else {
        camX = Math.min(camX, GameSession.gameSession.map.realWidth() - camWidth);
        camY = Math.min(camY, GameSession.gameSession.map.realHeight() - camHeight);
        camX = Math.max(camX, 0);
        camY = Math.max(camY, 0);
        }



        if (camWidth > GameSession.gameSession.map.realWidth())
            camX = GameSession.gameSession.map.realWidth() / 2 - camWidth / 2;

        camX = precCamX + (camX - precCamX) * movingTime / 10;
        camY = precCamY + (camY - precCamY) * movingTime / 10;

        camWidth = precCamWidth + (camWidth - precCamWidth) * movingTime / 10;
        camHeight = precCamHeight + (camHeight - precCamHeight) * movingTime / 10;

    }

    public void paint(Graphics g) {
        if (debugMode) {
            g.setColor(Color.pink);
            for (int i = 0; i < 5; i++)
                g.drawRect((int)camX + i, (int)camY + i, (int)camWidth - i * 2, (int)camHeight - i * 2);
        }
    }

    static public void drawImage(Graphics g, BufferedImage img, int x, int y, int width, int height) {
        if ((x + Math.abs(width) < camera.camX || x > camera.camX + camera.camWidth
                || y + Math.abs(height) < camera.camY || y > camera.camY + camera.camHeight)
                && Opti)
            return;
        if (width < 0)
            x -= width;
        if (height < 0)
            y -= height;
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
        int x = e.x;
        int y = e.y;
        int width = img.getWidth();
        int height = img.getHeight();
        int hitboxOffsetX = e.hitbox.offsetX;
        int hitboxWidth = e.hitbox.width;

        if (e.facingDirection.x < 0) {
            int inverseX = x - width + 2 * hitboxOffsetX + hitboxWidth;

            drawImage(g, img, inverseX, y, -width, height);
        } else {
            drawImage(g, img, x, y, width, height);
        }
    }

    static public void drawImage(Graphics g, BufferedImage img, int x, int y, int width, int height, boolean invertedX,
            boolean invertedY) {
        if (invertedX && invertedY) {
            drawImage(g, img, x, y, -width, -height);
        } else if (invertedX) {
            drawImage(g, img, x, y, -width, height);
        } else if (invertedY) {
            drawImage(g, img, x, y, width, -height);
        } else {
            drawImage(g, img, x, y, width, height);
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

    static public void drawText(Graphics g, int x, int y, String str) {
        if (debugMode) {
            g.drawString(str, x, y);
        } else {
            int cX = onCamViewX(x, camera.scale);
            int cY = onCamViewY(y, camera.scale);
            g.drawString(str, cX, cY);
        }
    }

    static public void fillRect(Graphics g, int x, int y, int width, int height) {
        if (debugMode) {
            g.fillRect(x, y, width, height);
        } else {
            int cX = onCamViewX(x, camera.scale);
            int cY = onCamViewY(y, camera.scale);
            int cWidth = realSize(width, camera.scale);
            int cHeight = realSize(height, camera.scale);
            g.fillRect(cX, cY, cWidth, cHeight);
        }

    }
}
