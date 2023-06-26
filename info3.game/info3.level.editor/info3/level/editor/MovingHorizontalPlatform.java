package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class MovingHorizontalPlatform extends AnimatedEntity {
    int speed ;
    int blockMove ;
    public MovingHorizontalPlatform() throws IOException {
        this(1, 3);
    }
    public MovingHorizontalPlatform(int speed, int blockMove) throws IOException {
        super("resources/blocks/obsidian.png");
        //TODO Auto-generated constructor stub
        this.speed = speed;
        this.blockMove = blockMove;
    }

    @Override
    public String toString() {
        return "MovingHorizontalPlatform";
    }

    @Override
    public void setTags(JSONObject tags) {
        tags.put("blockMove", blockMove);
        tags.put("speed", speed);
    }
    
    
}
