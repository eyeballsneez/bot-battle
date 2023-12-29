import java.util.ArrayList;

public abstract class actor {

    protected double x;
    protected double y;
    ArrayList<actor> allies;
    ArrayList<actor> enemies;

    public actor(ArrayList<actor> allies, ArrayList<actor> enemies)
    {
        this.allies = allies;
        this.enemies = enemies;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public abstract void tick();
}
