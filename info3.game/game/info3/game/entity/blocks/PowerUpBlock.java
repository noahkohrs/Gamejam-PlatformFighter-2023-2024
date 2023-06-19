package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.PowerUp;
import info3.game.entity.Block;

public class PowerUpBlock extends Block {

    public PowerUp powerUp;
    private static String blockTexture = "resources/blocks/powerUpBlock.png";

    public PowerUpBlock(int x, int y) throws IOException {
        super(x, y, blockTexture);
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

        if (powerUp == null) {
            String powerUpName = e.state.name;

            if (powerUpName.equals("Speed")) {
                powerUpName = "speed";
            } else if (powerUpName.equals("Ammo")) {
                powerUpName = "ammo";
            } else if (powerUpName.equals("Shield")) {
                powerUpName = "shield";
            } else if (powerUpName.equals("Pow")) {
                powerUpName = "power";
            }

            String powerUpPath = "resources/powerUp/" + powerUpName + ".png";
            try {
                this.powerUp = new PowerUp(x, y - Block.BLOCK_SIZE, powerUpPath, 1, 1, powerUpName);
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

    public void deletePowerUp() {
        powerUp.kill();
        powerUp = null;
    }

}
