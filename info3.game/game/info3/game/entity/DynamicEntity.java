package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;

public class DynamicEntity extends Entity {

    public DynamicEntity(int x, int y, int team, String filename, int nrows, int ncols) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        GameSession.gameSession.addEntity(this);
    }

    public DynamicEntity(int x, int y, int team) throws IOException {
        super(x, y, team);
        GameSession.gameSession.addEntity(this);
    }

    @Override
    public void move(Direction direction) {
        switch(direction){
            case UPPER:
              this.y-=10;
              break;
            case BOTTOM:
              this.y+=10;
              break;
            case RIGHT:
              this.x+=10;
              break;
            case LEFT:
              this.x-=10;
              break;
            default:
                break;
          }
    }

    @Override
    public void wizz() {
        System.out.println("We do not use wizz with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void wizz(String direction) {
        System.out.println("We do not use wizz with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void pop() {
        System.out.println("We do not use pop with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void egg(Entity type) {
        System.out.println("We do not use egg with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    public void kill() {
        GameSession.gameSession.removeEntity(this);
    }

    @Override
    public boolean gotPower() {
        System.out.println("We do not use gotPower with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public boolean gotStuff() {
        System.out.println("We do not use gotStuff with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public void turn() {
        System.out.println("We do not use turn with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public boolean cell(Direction direction, String category) {
        System.out.println("We do not use cell with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public boolean MyDir(String direction) {
        System.out.println("We do not use MyDir with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return false");
        return false;
    }

    @Override
    public void pick() {
        System.out.println("We do not use pick with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void jump(String direction) {
        System.out.println("We do not use jump with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    @Override
    public void hit(String direction) {
        System.out.println("We do not use hit with"+this.getClass().getSimpleName()+", so in order to not have a problem with the parser, it always return void");
        return;
    }

    public void takeDamage(int damage){}
}
