package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

abstract public class Block extends Element {

    public Block(String filename) throws IOException {
        super(filename);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void setTags(JSONObject tags) {
        tags.put("block", true);
    }
    
}
