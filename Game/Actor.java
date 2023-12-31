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
}
