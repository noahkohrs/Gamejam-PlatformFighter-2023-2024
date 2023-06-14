package info3.game.entity;

import java.awt.image.BufferedImage;

public class EntityView {
    public BufferedImage[] images;
    public int imageIndex ;
    int width, height ;
    
    EntityView(BufferedImage[] images) {
        this.images = images ;
        this.width = images[0].getWidth() ;
        this.height = images[0].getHeight() ;
    }

    public EntityView(String filename, int nrows, int ncols) {
        try {
            images = Entity.loadSprite(filename, nrows, ncols);
            this.width = images[0].getWidth() ;
            this.height = images[0].getHeight() ;
        } catch (Exception e) {
            System.out.println("Error loading sprite: " + filename);
            System.exit(1);
        }
    }

    public BufferedImage getImage() {
        return images[imageIndex] ;
    }

    public void tick(long elapsed) {
    }
}
