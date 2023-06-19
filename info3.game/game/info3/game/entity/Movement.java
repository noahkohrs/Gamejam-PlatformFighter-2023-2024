package info3.game.entity;

import info3.game.GameSession;

public class Movement {

    static public void Walk(Entity E) {
        E.updateVelocityX();
        E.x += E.velX * E.facingDirection.x;
        while (E.hitbox.inCollision(Direction.LEFT))
            E.x += 1;
        while (E.hitbox.inCollision(Direction.RIGHT))
            E.x -= 1;
        E.affectTor();
    }

    public static void manageAirJump(Entity E) {
            E.y += 1;
            if (!E.hitbox.inCollision(Direction.BOTTOM) && E.jumpCounter == E.jumpAmount) {
                E.jumpCounter-=1;
                if (E.jumpCounter == 0) {
                    return;
                }
            }
            E.y -= 1;
    }

    public static void Jump(Player E) {
        if (E.jumpCounter > 0 && E.jumpCooldown < 0) {
            manageAirJump(E);
            E.velY = PhysicConstant.jumpForce;
            E.jumpCounter--;
            E.jumpCooldown = 250;
        }
    }

    public static void affectGravity(Player E) {
        E.updateJumpVelocity();
        E.updateVelocityY();
        E.y -= E.velY;
        while (E.hitbox.inCollision(Direction.UPPER)) {
            E.y += 1;
            E.velY = Math.min(0, E.velY);
        }
        while (E.hitbox.inCollision(Direction.BOTTOM)) {
            E.y -= 1;
            E.velY = PhysicConstant.gravity;
            E.jumpCounter = E.jumpAmount;
        }
    }


}
