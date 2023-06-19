package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.Malus;

import info3.game.entity.TEAM;



public class MalusBlock extends Block{

    public Malus malus;
    float timer = 0;
    private static String blockTexture = "resources/blocks/malusBlock.png";


    public MalusBlock(int x, int y) throws IOException{
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
