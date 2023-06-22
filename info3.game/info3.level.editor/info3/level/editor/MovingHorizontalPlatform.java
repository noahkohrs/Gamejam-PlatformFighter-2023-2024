package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class MovingHorizontalPlatform extends AnimatedEntity {

    public MovingHorizontalPlatform() throws IOException {
        super("resources/blocks/obsidian.png");
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "MovingHorizontalPlatform";
    }

    @Override
    public void setTags(JSONObject tags) {
        tags.put("blockMove", 3);
        tags.put("speed", 1);
    }
    
    
}
