import java.util.ArrayList;

public class infantryGuy extends guy {
    int gunCooldown;
    int gunMaxCooldown = 10;
    public infantryGuy(double startx, double starty, ArrayList<actor> allies, ArrayList<actor> enemies){
        super(startx, starty, 5, 5, allies, enemies);
    }

    public void shoot(){
        gunCooldown = gunMaxCooldown;
        allies.add(new projectile(x, y, 20, direction, 1, 5, allies, enemies));
    }

    public void tick(){
        gunCooldown -= 1;
        super.move();
    }
    public void draw(){

    }
}
