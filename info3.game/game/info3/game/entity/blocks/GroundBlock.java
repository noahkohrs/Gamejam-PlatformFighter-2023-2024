package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;

public class GroundBlock extends Block {
    private static String blockTexture = "resources/blocks/ground.png";
    
    public GroundBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
    }    
}
