package Game;

import java.util.ArrayList;

public class Base extends Actor {

    public static final int infantryCost = 250;
    public static final int minerCost = 500;
    private int money;

    public Base(int startingMoney, double xpos, double ypos, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(allies, enemies, game);
        money = startingMoney;
        x = xpos;
        y = ypos;
    }

    public void spawnInfantryGuy() {
        if (money >= infantryCost) {
            money -= infantryCost;
            InfantryGuy infantry = new InfantryGuy(x, y, allies, enemies, game);
            game.add(infantry, allies);
        }

    }


    public void spawnMineGuy() {
        if (money >= minerCost) {
            money -= minerCost;
            MinerGuy miner = new MinerGuy(x, y, allies, enemies, game);
            game.add(miner, allies);
        }

    }

    protected void addMoney(int cash) {
        money += cash;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public void tick() {

    }
}
