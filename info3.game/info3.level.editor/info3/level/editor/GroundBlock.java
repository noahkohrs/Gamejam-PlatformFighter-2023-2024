package info3.level.editor;

import java.io.IOException;

public class GroundBlock extends Block {

    public GroundBlock() throws IOException {
        super("resources/blocks/ground.png");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "GroundBlock";
    }
    
}
