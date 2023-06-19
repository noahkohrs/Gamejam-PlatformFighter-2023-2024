package info3.level.editor;

import java.io.IOException;


public class MalusBlock extends Block {
    public MalusBlock() throws IOException {
        super("resources/blocks/malusBlock.png");
    }

    public Element copy() throws IOException {
        return new MalusBlock();
    }

    public String toString() {
        return "MalusBlock";
    }
}
