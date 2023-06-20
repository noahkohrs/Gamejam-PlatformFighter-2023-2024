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
    public RaptorView(String filename, int nrows, int ncols, Raptor entity) {
        super(filename,2,8,entity);
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
    }
}

