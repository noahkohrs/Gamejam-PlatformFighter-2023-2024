package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;

public class LeaveBlock extends Block {
    
    private static String blockTexture = "resources/blocks/leaveBlock.png";
    
    public LeaveBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
        solid = false ;
    }    
    
}
