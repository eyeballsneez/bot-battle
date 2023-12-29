public class base extends actor {
    private int money;
    private int income;

    public base(int startingMoney, int startingIncome) {
        money = startingMoney;
        income = startingIncome;
    }

    public void tick() {
        money += income;
    }

    public void draw() {

    }

}
