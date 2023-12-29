import java.util.ArrayList;

public class base extends actor {
    private int money;
    private int income;

    public base(int startingMoney, int startingIncome, double xpos, double ypos, ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
        money = startingMoney;
        income = startingIncome;
        x = xpos;
        y = ypos;
    }

    public void invest(int amount) {
        if (amount < money){
            money -= amount;
            income += amount / 100;
        }
    }

    public void spawnInfantryGuy(){
        if (money >= 50)
        {
            money -= 50;
            allies.add(new infantryGuy(x, y, allies, enemies));
        }

    }

    public void tick() {
        money += income;
    }

    public void draw() {

    }

}
