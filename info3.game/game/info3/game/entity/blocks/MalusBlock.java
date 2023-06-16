package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.Malus;

import info3.game.entity.TEAM;



public class MalusBlock extends DynamicEntity{

    public Malus malus;
    float timer = 0;

    public MalusBlock(int x, int y, int nrows, int ncols) throws IOException{
        super(x, y, TEAM.NONE, "resources/blocks/malusBlock.png", 1, 1);
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
            String malusUpName = e.state.name;

            if (malusUpName.equals("Speed")) {
                malusUpName = "speed";
            } else if (malusUpName.equals("Ammo")) {
                malusUpName = "ammo";
            } else if (malusUpName.equals("Shield")) {
                malusUpName = "shield";
            } else if (malusUpName.equals("Pow")) {
                malusUpName = "power";
            }

            String malusUpPath = "resources/powerUp/" + malusUpName + ".png";
            try {
                this.malus = new Malus(x, y - Block.BLOCK_SIZE, malusUpPath, 1, 1);
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
    
}
