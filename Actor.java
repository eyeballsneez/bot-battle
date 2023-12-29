import java.lang.Math;

public class actor {

    int x;
    int y;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public tick();

    public draw();
}
public class base extends actor {
    private int money;
    private int income;

    public base(int startingMoney, int startingIncome){
        money = startingMoney;
        income = startingIncome;
    }

    public tick() {
        money += income;
    }

}

public class guy extends actor {
    private double direction;
    private int speed;
    private int maxSpeed;

    public tick() {
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
}