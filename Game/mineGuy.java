package Game;

import java.util.ArrayList;

public class mineGuy extends guy {

    public mineGuy(double startx, double starty, ArrayList<actor> allies, ArrayList<actor> enemies, Game game) {
        super(startx, starty, 5, 20, 5, allies, enemies, game);
    }

    public int getIncome(){
        if (this.getSpeed() == 0) {
            base base = (base) allies.get(0);
            double dist = Math.sqrt((y - base.getY()) * (y - base.getY()) + (x - base.getX()) * (x - base.getX()));
            return (int) Math.ceil(dist / 200);
        }
        return 0;
    }

    public void tick(){
        if (this.getSpeed() == 0) {
            base base = (base) allies.get(0);
            double dist = Math.sqrt((y - base.getY()) * (y - base.getY()) + (x - base.getX()) * (x - base.getX()));
            base.addMoney((int) Math.ceil(dist/200));
        }
        super.move();
    }
}
