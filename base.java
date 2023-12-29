public class base extends actor {
    private int money;
    private int income;

    public base(int startingMoney, int startingIncome, int xpos, int ypos) {
        money = startingMoney;
        income = startingIncome;
        x = xpos;
        y = ypos;
    }

    public void tick() {
        money += income;
    }

    public void draw() {

    }

}
