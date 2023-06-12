package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

abstract public class AnimatedEntity extends Element {

    public AnimatedEntity(String filename) throws IOException {
        super(filename);
    }

    public void setTags(JSONObject tags) {
        tags.put("animated", true);
    }
    
}
