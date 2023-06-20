package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.Malus;
import info3.game.entity.TEAM;

public class MalusBlock extends DynamicEntity {

    public Malus malus;
    float timer = 0;
    private static String blockTexture = "resources/blocks/malusBlock.png";

    public MalusBlock(int x, int y) throws IOException {
        super(x, y, TEAM.NONE, blockTexture, 1, 1);
    }

    @Override
    public void tick(long elapsed) {
        try {
            timer += elapsed;
            automate.step(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void egg(Entity e) {

        if (malus == null) {
            String malusName = e.state.name;

            if (malusName.equals("Speed")) {
                malusName = "speed";
            } else if (malusName.equals("Ammo")) {
                malusName = "ammo";
            } else if (malusName.equals("Shield")) {
                malusName = "shield";
            } else if (malusName.equals("Pow")) {
                malusName = "power";
            }

            String malusPath = "resources/powerUp/" + malusName + ".png";
            try {
                this.malus = new Malus(x, y - Block.BLOCK_SIZE, malusPath, 1, 1, malusName, this);
            } catch (IOException c) {
                c.printStackTrace();
            }
        }

    }

    @Override
    public boolean gotPower() {
        if (timer >= 5000) {
            timer = 0;
            return true;
        }
        return false;
    }

    public void deleteMalus() {
        malus.kill();
        malus = null;
    }

}
