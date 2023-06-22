package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;


public class PortalBlock extends AnimatedEntity {
    String type = "Green";
    public PortalBlock() throws IOException {
        super("resources/blocks/portal.png");
    }

    public void setTags(JSONObject tags) {
        tags.put("color", type);
    }

    @Override
    public String toString() {
        return "PortalBlock";
    }
    
}
