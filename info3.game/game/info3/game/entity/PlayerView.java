package info3.game.entity;

import java.awt.image.BufferedImage;

public class PlayerView extends EntityView {

    long m_imageElapsed = 0;

    PlayerView(BufferedImage[] images) {
        super(images);
    }

    public PlayerView(String string, int nrows, int ncols) {
        super(string, nrows, ncols);
    }

    public void tick(long elapsed) {
        m_imageElapsed += elapsed;
        if (m_imageElapsed > 200) {
            m_imageElapsed = 0;
            imageIndex = (imageIndex + 1) % images.length;
        }
    }

}
