import java.util.ArrayList;

public class base extends actor {
    private int money;
    private int income;

    public base(int startingMoney, int startingIncome, double xpos, double ypos) {
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

    public void tick(ArrayList<actor> allies, ArrayList<actor> enemies) {
        money += income;
    }

    public void draw() {

    }

}
