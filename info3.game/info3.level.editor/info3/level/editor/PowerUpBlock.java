package info3.level.editor;

import java.io.IOException;


public class PowerUpBlock extends Block {
    public PowerUpBlock() throws IOException {
        super("resources/blocks/powerUpBlock.png");
    }

    public Element copy() throws IOException {
        return new PowerUpBlock();
    }

    public String toString() {
        return "PowerUpBlock";
    }
}
