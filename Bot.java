import java.util.ArrayList;

public abstract class Bot {
    public ArrayList<actor> allies;
    private ArrayList<actor> enemies;
    public Bot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        this.allies = allies;
        this.enemies = enemies;
    }

    public abstract void tick();
}
