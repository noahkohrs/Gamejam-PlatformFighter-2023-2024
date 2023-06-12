package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class VoidBlock extends Block {

    public VoidBlock() throws IOException {
        super("");
    }

    public Element copy() throws IOException {
        return new VoidBlock();
    }

    public String toString() {
        return "VoidBlock";
    }
    
    public JSONObject toJSON() {
        return null;
    }
    
}
