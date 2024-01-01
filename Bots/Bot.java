package Bots;

import Game.*;
import java.util.ArrayList;

public abstract class Bot {
    protected ArrayList<Actor> allies;
    protected ArrayList<Actor> enemies;
    protected Game game;
    public Bot(ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        this.allies = allies;
        this.enemies = enemies;
        this.game = game;
    }

    public abstract void tick();
}
