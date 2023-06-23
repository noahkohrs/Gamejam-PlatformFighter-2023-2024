package info3.game.entity;

import java.io.IOException; 

public class Block extends Entity {
    public static final int BLOCK_SIZE = 32;

    public Block(int x, int y, String blockTexture) throws IOException {
        super(x*BLOCK_SIZE, y*BLOCK_SIZE, TEAM.NONE, blockTexture, 1, 1);
        solid = true;
    }

    @Override
    public void tick(long elapsed) {
        try {
            automate.step(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void wizz() {
        System.out.println("We do not use wizz with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void pop() {
        System.out.println("We do not use pop with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public boolean gotPower() {
        System.out.println("We do not use gotpower with block, so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public void turn() {
        System.out.println("We do not use turn with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public boolean cell(Direction direction, String category) {
        System.out.println("We do not use cell with block, so in order to not have a problem with the parser, it always return false");
        return false;
    }
    

    @Override
    public void egg(Entity type) {
        System.out.println("We do not use egg with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public boolean MyDir(String direction) {
        System.out.println("We do not use MyDir with block, so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public void pick() {
        System.out.println("We do not use pick with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void wizz(String direction) {
        System.out.println("We do not use wizz with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public boolean gotStuff() {
        System.out.println("We do not use gotStuff with block, so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public void jump(String direction) {
        System.out.println("We do not use jump with block, so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void hit(String direction) {
        System.out.println("We do not use hiy with block, so in order to not have a problem with the parser, it always return void");
        return;
    }
}
