package Game;

import java.util.ArrayList;

public class InfantryGuy extends Guy {
    protected int gunCooldown;
    public static final int gunMaxCooldown = 50;
    public static final int range = 30;

    public static final int bulletSpeed = 10;
    public InfantryGuy(double startx, double starty, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game){
        super(startx, starty, 5, 20, 5, allies, enemies, game);
    }

    public void shoot() {
        if (gunCooldown > 0) return;

        gunCooldown = gunMaxCooldown;
        game.add(new Projectile(this.x, this.y, bulletSpeed, direction, 1, 5, range, allies, enemies, game), allies);
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
