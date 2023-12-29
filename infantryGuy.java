import java.util.ArrayList;

public class infantryGuy extends guy {
    int gunCooldown;
    int gunMaxCooldown = 10;
    public infantryGuy(int startx, int starty){
        super(startx, starty, 5, 5);
    }

    public void shoot(){
        gunCooldown = gunMaxCooldown;
    }

    public void tick(ArrayList<actor> allies, ArrayList<actor> enemies){
        gunCooldown -= 1;
        super.move();
    }
    public void draw(){

    }
}
