package Game;

import java.util.ArrayList;

public class base extends actor {
    private int money;

    public base(int startingMoney, double xpos, double ypos, ArrayList<actor> allies, ArrayList<actor> enemies, Game game) {
        super(allies, enemies, game);
        money = startingMoney;
        x = xpos;
        y = ypos;
    }

    public void spawnInfantryGuy() {
        if (money >= 150) {
            money -= 150;
            infantryGuy infantry = new infantryGuy(x, y, allies, enemies, game);
            game.add(infantry, allies);
        }

    }


    public void spawnMineGuy() {
        if (money >= 500) {
            money -= 500;
            mineGuy miner = new mineGuy(x, y, allies, enemies, game);
            game.add(miner, allies);
        }

    }

    protected void addMoney(int cash) {
        cash += money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public void tick() {

    }
}
