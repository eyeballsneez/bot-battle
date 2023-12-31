package Bots;


import Game.Actor;
import Game.Base;
import Game.Guy;

import java.util.ArrayList;

public class salBot extends Bot {
    public salBot(ArrayList<Actor> allies, ArrayList<Actor> enemies) {
        super(allies, enemies);
    }

    int income;

    @Override
    public void tick() {
        for (Actor actor : allies) {
            if (actor instanceof Base base) {
                if (allies.size() < 4) base.spawnInfantryGuy();
            }

            if (!(actor instanceof Guy)) continue;

            Guy guy = (Guy) actor;
            guy.setSpeed(4);
            guy.setDirection(guy.getDirection() + (Math.random() - 0.5) * Math.PI);
        }
    }
}
