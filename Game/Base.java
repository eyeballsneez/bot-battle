package Game;

import java.util.ArrayList;

public class Base extends Actor implements Damageable {

    public static final int infantryCost = 250;
    public static final int minerCost = 500;

    public static final int size = 60;

    public static final double maxHp = 5000;
    private int money;

    private double hp;

    public Base(int startingMoney, double xpos, double ypos, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(allies, enemies, game);
        money = startingMoney;
        x = xpos;
        y = ypos;
        hp = maxHp;
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
    public void damage(double dmg){
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
        }
    }
    @Override
    public double getMaxHp() {
        return maxHp;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public double getHp() {
        return hp;
    }

    @Override
    public void tick() {

    }
}
