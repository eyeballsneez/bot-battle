package Game;

import java.util.ArrayList;

public abstract class Actor {

    protected double x;
    protected double y;
    protected ArrayList<Actor> allies;
    protected ArrayList<Actor> enemies;

    Game game;

    public Actor(ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game)
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

    public double distance(Actor other) {
        return distance(other.getX(), other.getY());
    }

    public double distance(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return dx * dx + dy * dy;
    }
}
