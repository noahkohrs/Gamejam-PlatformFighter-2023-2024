package info3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.Camera;
import info3.game.automate.Automate;

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
        this.view = new EntityView(filename, nrows, ncols);

        this.automate = automate;
    }

    public abstract void tick(long elapsed) ;

    public void paint(Graphics g) {
        BufferedImage img = getImage();
        Camera.drawImage(g, img, x, y, getWidth(), getHeight());
        // OR
        // Camera.drawEntity(this, g); (same result)
    }

    public abstract void move(String direction);

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
}
