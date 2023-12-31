package Game;

import java.util.ArrayList;

public class base extends actor {
    private int money;
    private int income;

    public base(int startingMoney, int startingIncome, double xpos, double ypos, ArrayList<actor> allies, ArrayList<actor> enemies, Game game) {
        super(allies, enemies, game);
        money = startingMoney;
        income = startingIncome;
        x = xpos;
        y = ypos;
    }

    public void invest(int amount) {
        if (amount <= money) {
            money -= amount;
            income += amount / 100;
        }
    }

    public void spawnInfantryGuy() {
        if (money >= 150) {
            money -= 150;
            infantryGuy infantry = new infantryGuy(x, y, allies, enemies, game);
            game.add(infantry, allies);
        }

    }

<<<<<<< Updated upstream
=======

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

>>>>>>> Stashed changes
    public int getMoney() {
        return money;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public void tick() {
        money += income;
    }
}
