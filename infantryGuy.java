import java.util.ArrayList;

public class infantryGuy extends guy {
    int gunCooldown;
    static final int gunMaxCooldown = 1;
    public infantryGuy(double startx, double starty, ArrayList<actor> allies, ArrayList<actor> enemies){
        super(startx, starty, 5, 5, allies, enemies);
    }

    public void shoot(){
        if (gunCooldown > 0) return;

        gunCooldown = gunMaxCooldown;
        allies.add(new projectile(this.x, this.y, 10, direction, 1, 5, allies, enemies));
    }

    @Override
    public void tick(){
        gunCooldown -= 1;
        super.move();
    }
}
