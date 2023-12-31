package Bots;


import Game.actor;
import Game.base;
import Game.guy;
import Game.infantryGuy;

import java.util.ArrayList;

public class salBot extends Bot {
    public salBot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
    }

    int income;

    @Override
    public void tick() {
        for (actor actor : allies) {
            if (actor instanceof base base) {
                if (allies.size() < 4) base.spawnInfantryGuy();
            }

            if (!(actor instanceof guy)) continue;

            guy guy = (guy) actor;
            guy.setSpeed(4);
            guy.setDirection(guy.getDirection() + (Math.random() - 0.5) * Math.PI);
        }
    }
}
