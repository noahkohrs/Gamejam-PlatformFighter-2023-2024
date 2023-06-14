package info3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.Camera;
import info3.game.GameSession;
import info3.game.automate.Automate;
import info3.game.hitbox.HitBox;

public abstract class Entity {
    public int x;
    public int y;
    long moveElapsed;

    Automate automate;
    HitBox hitbox;
    public EntityView view;

    public Entity(int x, int y, Automate automate, String filename, int nrows, int ncols) throws IOException {
        this.x = x;
        this.y = y;
        this.view = new EntityView(filename, nrows, ncols, this);

        this.automate = automate;
    }

    public abstract void tick(long elapsed);

    public abstract void move(Direction direction);

    public abstract void wizz();

    public abstract void pop();

    public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
        File imageFile = new File(filename);
        if (imageFile.exists()) {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth(null) / ncols;
            int height = image.getHeight(null) / nrows;

            BufferedImage[] images = new BufferedImage[nrows * ncols];
            for (int i = 0; i < nrows; i++) {
                for (int j = 0; j < ncols; j++) {
                    int x = j * width;
                    int y = i * height;
                    images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
                }
            }
            return images;
        }
        return null;
    }

    public BufferedImage getImage() {
        return view.getImage();
    }

    public int getWidth() {
        return view.width;
    }

    public int getHeight() {
        return view.height;
    }

    protected void affectTor() {
        if (Camera.centeredCoordinateX(this) < 0) {
            x = GameSession.gameSession.map.realWidth() - getWidth();
        }
        if (Camera.centeredCoordinateX(this) > GameSession.gameSession.map.realWidth()) {
            x = 0;
        }
        if (Camera.centeredCoordinateY(this) < 0) {
            y = GameSession.gameSession.map.realHeight() - getHeight();
        }
        if (Camera.centeredCoordinateY(this) > GameSession.gameSession.map.realHeight()) {
            y = 0;
        }
    }
}
