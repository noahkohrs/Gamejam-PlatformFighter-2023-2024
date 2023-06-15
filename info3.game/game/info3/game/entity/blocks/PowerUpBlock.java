package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.DynamicEntity;
import info3.game.entity.PowerUp;



public class PowerUpBlock extends DynamicEntity{

    public PowerUp powerUp;

    public PowerUpBlock(int x, int y, int nrows, int ncols) throws IOException{
        super(x, y, "resources/blocks/powerUpBlock.png", 1, 1);
    }


    @Override
    public void tick(long elapsed) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void egg() {
        
    }

    public boolean GotPower(){
        return true;
    }

    
}
