package info3.level.editor;

import java.io.IOException;

public class LeaveBlock extends Block{
    public LeaveBlock() throws IOException {
        super("resources/blocks/leaveBlock.png");
    }
    @Override
    public String toString() {
        return "LeaveBlock";
    }
}
