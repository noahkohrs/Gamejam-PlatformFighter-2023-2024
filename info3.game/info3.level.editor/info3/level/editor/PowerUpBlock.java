package info3.level.editor;

import java.io.IOException;


public class PowerUpBlock extends AnimatedEntity {
    public PowerUpBlock() throws IOException {
        super("resources/blocks/powerUpBlock.png");
    }


    public String toString() {
        return "PowerUpBlock";
    }
}
