package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class SpawnerPoint extends AnimatedEntity {
    
        public SpawnerPoint() throws IOException {
            super("resources/blocks/3.png");
        }
    
        public Element copy() throws IOException {
            return new SpawnerPoint();
        }
    
        public String toString() {
            return "SpawnerPoint";
        }

    
}
