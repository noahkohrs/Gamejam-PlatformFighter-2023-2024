package info3.level.editor;

import java.io.IOException;

public class woodBlock extends Block {
    public woodBlock() throws IOException {
        super("resources/blocks/woodBlock.png");
    }
    @Override
    public String toString() {
        return "WoodBlock";
    }
        
}
