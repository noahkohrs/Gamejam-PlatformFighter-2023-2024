package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;

public class WoodBlock extends Block {
    
    private static String blockTexture = "resources/blocks/woodBlock.png";
    
    public WoodBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
    }    
    
}
