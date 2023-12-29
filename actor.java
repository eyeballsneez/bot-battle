import java.util.ArrayList;

public abstract class actor {

    protected double x;
    protected double y;
    ArrayList<actor> allies;
    ArrayList<actor> enemies;

    Game game;

    public actor(ArrayList<actor> allies, ArrayList<actor> enemies, Game game)
    {
        this.allies = allies;
        this.enemies = enemies;
        this.game = game;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public abstract void tick();
}
