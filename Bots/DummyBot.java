package Bots;
import Game.*;

import java.util.ArrayList;

public class DummyBot extends Bot {
    public DummyBot(ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(allies, enemies, game);
    }

    @Override
    public void tick() {
        for (Actor actor : allies) {
            if (actor instanceof Base base) {
                if (allies.size() < 4) base.spawnInfantryGuy();
            }

            if (!(actor instanceof Guy guy)) continue;

            guy.setSpeed(4);
            guy.setDirection(guy.getDirection() + (Math.random() - 0.5) * Math.PI);
        }
    }
}
