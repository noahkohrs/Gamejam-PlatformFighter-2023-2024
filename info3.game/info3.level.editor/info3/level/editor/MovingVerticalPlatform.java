package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class MovingVerticalPlatform extends AnimatedEntity {
    int speed;
    int blockMove;
    public MovingVerticalPlatform() throws IOException {
        this(1, 3);

    }

    public MovingVerticalPlatform(int speed, int blockMove) throws IOException {
        super("resources/blocks/movingVerticalPlatform.png");
        this.speed = speed;
        this.blockMove = blockMove;
    }

    @Override
    public String toString() {
        return "MovingVerticalPlatform";
    }

    @Override
    public void setTags(JSONObject tags) {
        tags.put("blockMove", blockMove);
        tags.put("speed", speed);
    }
    
    
}
