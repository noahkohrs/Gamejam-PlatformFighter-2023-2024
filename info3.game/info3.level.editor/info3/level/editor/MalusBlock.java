package info3.level.editor;

import java.io.IOException;


public class MalusBlock extends AnimatedEntity {
    public MalusBlock() throws IOException {
        super("resources/blocks/malusBlock.png");
    }


    public String toString() {
        return "MalusBlock";
    }
}
