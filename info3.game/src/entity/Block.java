package entity;

import java.awt.Graphics;
import java.io.IOException;

public class Block extends Entity {

    public Block(int x, int y, String blockTexture) throws IOException {
        super(x, y, null, blockTexture, 1, 1);
    }

    @Override
    public void tick(long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(m_images[0], x * width, y * height, width, height, null);
    }

    @Override
    public void camPaint(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'camPaint'");
    }

    @Override
    public void move(String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void wizz() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'wizz'");
    }

    @Override
    public void pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }
}
