package Game;

import java.util.ArrayList;

public class MinerGuy extends Guy {

    public MinerGuy(double startx, double starty, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(startx, starty, 5, 20, 5, allies, enemies, game);
    }

    public int getIncome() {
        Base base = (Base) allies.get(0);
        double dist = Math.sqrt((y - base.getY()) * (y - base.getY()) + (x - base.getX()) * (x - base.getX()));
        for (int i = 0; i < allies.size(); i++) {
            Actor ally = allies.get(i);
            if (ally instanceof MinerGuy && ally != this) {
                double disttemp = Math.sqrt((y - ally.getY()) * (y - ally.getY()) + (x - ally.getX()) * (x - ally.getX()));
                if (disttemp < dist){
                    dist = disttemp;
                }
            }
        }

        return (int) Math.floor(dist / 400);
    }

    public void tick() {
        if (this.getSpeed() == 0) {
            Base base = (Base) allies.get(0);
            base.addMoney(getIncome());
        }
        super.move();
    }
}
