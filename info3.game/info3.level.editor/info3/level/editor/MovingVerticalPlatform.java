package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class MovingVerticalPlatform extends AnimatedEntity {

    public MovingVerticalPlatform() throws IOException {
        super("resources/blocks/movingVerticalPlatform.png");
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "MovingVerticalPlatform";
    }

    @Override
    public void setTags(JSONObject tags) {
        tags.put("blockMove", 3);
        tags.put("speed", 1);
    }
    
    
}
