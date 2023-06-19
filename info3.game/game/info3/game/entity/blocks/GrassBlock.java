package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;

public class GrassBlock extends Block {
    private static String blockTexture = "resources/blocks/grass.png";
    
    public GrassBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
    }
    
}
