import java.lang.Math;
import java.util.ArrayList;

public abstract class guy extends actor {
    protected double direction;
    private double speed;
    private double maxSpeed;
    private double hp;
    private double maxHp;

    public guy(double startx, double starty, double startMaxSpeed, double maxHP, ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
        x = startx;
        y = starty;
        maxSpeed = startMaxSpeed;
        hp = maxHP;
        this.maxHp = maxHP;
    }

    protected void move() {
        x += Math.sin(direction) * speed;
        y += Math.cos(direction) * speed;
    }

    public void damage(double dmg){
        hp -= dmg;
        allies.remove(this);
        if (hp < 2) {
            allies.remove(this);
        }
    }

    @Override
    public void tick(){
        if (hp <= 0){
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

    public double getHp() {
        return hp;
    }
    public double getMaxHp() {
        return maxHp;
    }

    public double getDirection() {
        return direction;
    }
    public double getSpeed() {
        return speed;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
}
