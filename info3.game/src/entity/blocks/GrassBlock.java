package entity.blocks;

import java.io.IOException;

import entity.Block;

public class GrassBlock extends Block {
    private static String blockTexture = "resources/blocks/1.png";
    
    public GrassBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
    }
    
}
