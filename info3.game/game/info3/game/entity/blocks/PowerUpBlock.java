package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.PowerUp;
import info3.game.entity.TEAM;
import info3.game.entity.Block;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUpBlock extends DynamicEntity {

    public PowerUp powerUp;
    float timer = 0;

    public PowerUpBlock(int x, int y, int nrows, int ncols) throws IOException {
        super(x, y, TEAM.NONE, "resources/blocks/powerUpBlock.png", 1, 1);
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

            Random random = new Random();
            int r = random.nextInt(4);
            System.out.println(r);

            if (r == 0) {
                powerUpName = "speed";
            } else if (r == 1) {
                powerUpName = "ammo";
            } else if (r == 2) {
                powerUpName = "shield";
            } else if (r == 3) {
                powerUpName = "power";
            }

            System.out.println("egg");

            String powerUpPath = "resources/powerUp/" + powerUpName + ".png";
            try {
                this.powerUp = new PowerUp(x, y - Block.BLOCK_SIZE, powerUpPath, 1, 1);
            } catch (IOException c) {
                c.printStackTrace();
            }
        }

    }

    @Override
    public boolean gotPower() {
        // System.out.println(timer);
        if (timer >= 5000) { // 2 transitions donc /2 le temps souhaité
            timer = 0;
            return true;
        }

        return false;
    }

}
