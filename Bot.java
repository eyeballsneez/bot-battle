import java.util.ArrayList;

public abstract class Bot {
    protected ArrayList<actor> allies;
    protected ArrayList<actor> enemies;
    public Bot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        this.allies = allies;
        this.enemies = enemies;
    }

    public abstract void tick();
}
