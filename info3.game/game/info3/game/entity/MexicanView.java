package info3.game.entity;

import info3.game.GameSession;

public class MexicanView extends PlayerView {
    static String filename = "resources/Mexicain/PlayerSprite.png";
    Mexican mex;

    MexicanView(Mexican entity) {
        super(filename, 3, 2, entity);
        mex = entity;
    }

    public void tick(long elapsed) {
        if (mex.tequillatequen) {
            paintTequilla(mex, elapsed);
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

    private void paintTequilla(Mexican mex, long elapsed) {
        if (mex.timeDrink < mex.timeDrinkfinal / 2) {
            mex.view.imageIndex = 4;
            mex.timeDrink += elapsed;
        } else if (mex.timeDrink < mex.timeDrinkfinal) {
            mex.view.imageIndex = 5;
            mex.timeDrink += elapsed;
        } else {
            mex.tequillatequen = false;
            mex.timeDrink = 0;
        }
    }
}
