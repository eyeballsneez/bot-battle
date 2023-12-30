package Bots;

import Game.actor;
import Game.base;
import Game.guy;

import java.util.ArrayList;

public class DummyBot extends Bot {
    public DummyBot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
    }

    @Override
    public void tick() {
        for (actor actor : allies) {
            if (actor instanceof base base) {
                if (allies.size() < 4) base.spawnInfantryGuy();
            }

            if (!(actor instanceof guy guy)) continue;

            guy.setSpeed(4);
            guy.setDirection(guy.getDirection() + (Math.random() - 0.5) * Math.PI);
        }
    }
}
