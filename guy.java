import java.lang.Math;
import java.util.ArrayList;

public abstract class guy extends actor {
    protected double direction;
    private double speed;
    private double maxSpeed;
    private double hp;
    private double maxHp;
    private double size;

    public guy(double startx, double starty, double startMaxSpeed, double size, double maxHP, ArrayList<actor> allies, ArrayList<actor> enemies, Game game) {
        super(allies, enemies, game);
        x = startx;
        y = starty;
        maxSpeed = startMaxSpeed;
        hp = maxHP;
        this.maxHp = maxHP;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    protected void move() {
        x += Math.sin(direction) * speed;
        y += Math.cos(direction) * speed;
    }

    public void damage(double dmg){
        hp -= dmg;
        if (hp <= 0) {
            game.remove(this);
        }
    }

    @Override
    public void tick(){

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
