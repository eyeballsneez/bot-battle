import java.util.ArrayList;

public class DroneBot extends Bot {

    public DroneBot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
    }

    @Override
    public void tick() {
        for (actor ally : allies) {
            if (ally instanceof base) {
                ((base) ally).spawnInfantryGuy();
            }
        }
    }
}
