package Game;

import java.util.ArrayList;

public class MinerGuy extends Guy {

    public MinerGuy(double startx, double starty, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(startx, starty, 5, 20, 5, allies, enemies, game);
    }

    public int getIncome() {
        Base base = (Base) allies.get(0);
        double dist = distance(base);

        for (Actor ally : allies) {
            if (ally instanceof MinerGuy && ally != this) {
                double distTemp = distance(ally);

                if (distTemp < dist) {
                    dist = distTemp;
                }
            }
        }

        if (dist > 400) return 1;
        else return 0;
    }

    public void tick() {
        if (this.getSpeed() == 0) {
            Base base = (Base) allies.get(0);
            base.addMoney(getIncome());
        }
        super.move();
    }
}
