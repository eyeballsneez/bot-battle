import java.util.ArrayList;

abstract class actor {

    protected double x;
    protected double y;



    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public abstract void tick(ArrayList<actor> allies, ArrayList<actor> enemies);

    public abstract void draw();
}
