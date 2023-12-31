package Game;

import java.util.ArrayList;

public class infantryGuy extends guy {
    protected int gunCooldown;
    static final int gunMaxCooldown = 51;
    static final int range = 30;
    public infantryGuy(double startx, double starty, ArrayList<actor> allies, ArrayList<actor> enemies, Game game){
        super(startx, starty, 5, 20, 5, allies, enemies, game);
    }

    public void shoot() {
        if (gunCooldown > 0) return;

        gunCooldown = gunMaxCooldown;
        game.add(new projectile(this.x, this.y, 10, direction, 1, 5, range, allies, enemies, game), allies);
    }

    @Override
    public void tick(){
        gunCooldown -= 1;
        super.move();
    }

    public int getGunCooldown() {
        return gunCooldown;
    }
}
