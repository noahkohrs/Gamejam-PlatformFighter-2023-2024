package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class SpawnerPoint extends Block {
    
        public SpawnerPoint() throws IOException {
            super("resources/blocks/1.png");
        }
    
    
        public String toString() {
            return "SpawnerPoint";
        }    
}
