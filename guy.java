import java.lang.Math;

public class guy extends actor {
    private double direction;
    private int speed;
    private int maxSpeed;

    public void tick() {
        this.x += Math.sin(direction) * speed;
        this.y += Math.cos(direction) * speed;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        if (Math.abs(speed) < maxSpeed) {
            this.speed = speed;
        }
    }

    public void draw() {

    }

}
