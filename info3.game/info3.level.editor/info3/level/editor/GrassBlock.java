package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class GrassBlock extends Block {


    public GrassBlock() throws IOException {
        super("resources/blocks/grass.png");
    }
    
    public Element copy() throws IOException {
        return new GrassBlock();
    }
    
    public String toString() {
        return "GrassBlock";
    }

}
