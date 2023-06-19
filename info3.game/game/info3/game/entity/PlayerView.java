package info3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.Camera;

public class PlayerView extends EntityView {

    int deltatime = 0;
    long m_imageElapsed = 0;

    PlayerView(BufferedImage[] images, Player player) {
        super(images, player);
    }

    public PlayerView(String string, int nrows, int ncols, Player player) {
        super(string, nrows, ncols, player);
    }

    public void tick(long elapsed) {
        m_imageElapsed += elapsed;
        if (m_imageElapsed > 200) {
             m_imageElapsed = 0;
            if(((Player)entity).accelerationX == 0.1){
                imageIndex = (imageIndex + 1) % 2;
            } else {
                imageIndex= (imageIndex +1 )%4;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // Camera.drawImage(g, getImage(), entity.x, entity.y, entity.getWidth(), entity.getHeight());
        entity.hitbox.showHitBox(g);
        ((Player)entity).weapon.showHitBox(g);
        ((Player)entity).lifeBar.showLifeBar(g);

        //just here for directions
         if(((Player)entity).facingDirection==Direction.LEFT){
            Camera.drawImage(g, getImage(), entity.x, entity.y, entity.getWidth(), entity.getHeight(),false,false);
        }
        else{
            Camera.drawImage(g, getImage(), entity.x, entity.y, entity.getWidth(), entity.getHeight(),true,false);
        }

    }
}

