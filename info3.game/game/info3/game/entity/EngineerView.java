package info3.game.entity;

import info3.game.GameSession;

public class EngineerView extends PlayerView {
    static String filename = "resources/Ingenieur/PlayerSprite.png";
    Engineer engineer;

    EngineerView(Engineer entity) {
        super(filename, 3, 2, entity);
        this.engineer = entity;
    }

    public void tick(long elapsed) {
        if (engineer.weapon.getClass().getSimpleName().equals("Bazooka")) {
            imageIndex = 5;
        } else {
            m_imageElapsed += elapsed;
            if (m_imageElapsed > 200) {
                m_imageElapsed = 0;
                if (((Player) entity).accelerationX == 0.1) {
                    imageIndex = (imageIndex + 1) % 2;
                } else {
                    imageIndex = (imageIndex + 1) % 4;
                }
            }
        }
    }
}
