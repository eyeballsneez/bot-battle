import java.util.ArrayList;

public class infantryGuy extends guy {
    int gunCooldown;
    int gunMaxCooldown = 10;
    public infantryGuy(int startx, int starty, ArrayList<actor> allies, ArrayList<actor> enemies){
        super(startx, starty, 5, 5, allies, enemies);
    }

    public void shoot(){
        gunCooldown = gunMaxCooldown;
    }

    public void tick(){
        gunCooldown -= 1;
        super.move();
    }
    public void draw(){

    }
}
