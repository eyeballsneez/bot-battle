import java.time.LocalDateTime;
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
        if (amount < money){
            money -= amount;
            income += amount / 100;
        }
    }

    public void spawnInfantryGuy(){
        if (money >= 50)
        {
            money -= 50;
            infantryGuy infantry = new infantryGuy(x, y, allies, enemies, game);
            allies.add(infantry);
        }

    }

    public int getMoney() {
        return money;
    }

    @Override
    public void tick() {
        money += income;
    }
}
