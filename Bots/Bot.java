package Bots;

import Game.Actor;
import java.util.ArrayList;

public abstract class Bot {
    protected ArrayList<Actor> allies;
    protected ArrayList<Actor> enemies;
    public Bot(ArrayList<Actor> allies, ArrayList<Actor> enemies) {
        this.allies = allies;
        this.enemies = enemies;
    }

    public abstract void tick();
}
