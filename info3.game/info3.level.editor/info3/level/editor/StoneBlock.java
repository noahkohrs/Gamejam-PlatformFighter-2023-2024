package info3.level.editor;

import java.io.IOException;

public class StoneBlock extends Block {
    public StoneBlock() throws IOException {
        super("resources/blocks/stoneBlock.png");
    }

    @Override
    public String toString() {
        return "StoneBlock";
    }
}
