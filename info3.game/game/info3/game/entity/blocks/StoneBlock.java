package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;

public class StoneBlock extends Block {
    
    private static String blockTexture = "resources/blocks/stoneBlock.png";
    
    public StoneBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
    }    
    
}
