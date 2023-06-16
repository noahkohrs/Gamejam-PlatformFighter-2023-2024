package info3.game.entity;

import java.awt.image.BufferedImage;

public class RaptorView extends EntityView{

    private int runningCount ; 

    RaptorView(BufferedImage[] images, Entity entity) {
        super(images, entity);
        //TODO Auto-generated constructor stub
    }
    public RaptorView(String filename, int nrows, int ncols, Raptor entity) {
        super(filename,2,8,entity);
    }
    @Override
    public
    void tick(long elapsed) {
        runningCount+=elapsed;
        if (runningCount>120) {
            imageIndex= (imageIndex+1)%8;
            runningCount=0;
        }
            
    } 
}

