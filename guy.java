import java.lang.Math;
import java.util.ArrayList;

public abstract class guy extends actor {
    protected double direction;
    private double speed;
    private double maxSpeed;
    private double hp;

    public guy(double startx, double starty, double startMaxSpeed, double maxHP, ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
        x = startx;
        y = starty;
        maxSpeed = startMaxSpeed;
        hp = maxHP;
    }

    protected void move() {
        x += Math.sin(direction) * speed;
        y += Math.cos(direction) * speed;
    }

    public void damage(double dmg){
        hp -= dmg;
    }
    public void tick(ArrayList<actor> allies, ArrayList<actor> enemies){
        if (hp < 0){
            allies.remove(this);
        }
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        if (Math.abs(speed) < maxSpeed) {
            this.speed = speed;
        }
    }
}
