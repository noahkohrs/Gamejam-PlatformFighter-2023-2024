package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class MovingPlatform extends AnimatedEntity {

    public MovingPlatform() throws IOException {
        super("resources/blocks/obsidian.png");
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "MovingPlatform";
    }

    @Override
    public void setTags(JSONObject tags) {
        tags.put("blockMove", 3);
        tags.put("speed", 1);
    }
    
    
}
