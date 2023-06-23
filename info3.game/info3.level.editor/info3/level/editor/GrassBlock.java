package info3.level.editor;

import java.io.IOException;

public class GrassBlock extends Block {


    public GrassBlock() throws IOException {
        super("resources/blocks/grass.png");
    }
    
    
    public String toString() {
        return "GrassBlock";
    }

}
