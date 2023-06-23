package info3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.Camera;

public class RaptorView extends EntityView{

    private int runningCount ; 
    boolean attack=false;
    RaptorView(BufferedImage[] images, Entity entity) {
        super(images, entity);
        //TODO Auto-generated constructor stub
    }
    public RaptorView(String filename, int nrows, int ncols, Raptor raptor) {
        super(filename,2,8,raptor);
    }
    @Override
    public void tick(long elapsed) {
        runningCount+=elapsed;
        if (runningCount>120) {
            imageIndex= (imageIndex+1)%8;
            runningCount=0;
        }
        if(attack){
            imageIndex=(7+imageIndex+1)%16;
            runningCount=0;
        }            
    }
    
    @Override
    public void paint(Graphics g){
        entity.hitbox.showHitBox(g);
        Camera.drawEntity(entity, g);
        showLifeBar(g);
    }

    public void showLifeBar(Graphics g) {
        int lifeBarSize = 60;
        int barX = Camera.centeredCoordinateX(entity) - lifeBarSize/2;
        int barY = Camera.centeredCoordinateY(entity) - 25;
        g.setColor(java.awt.Color.GRAY);
        Camera.fillRect(g, barX, barY, lifeBarSize, 5);
        g.setColor(java.awt.Color.RED);
        lifeBarSize = (int) (lifeBarSize*(((Raptor)entity).getLifePercentage()))-2;
        Camera.fillRect(g, barX+1, barY+1, lifeBarSize, 4);
    }
}

