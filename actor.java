

abstract class actor {

    int x;
    int y;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public abstract void tick();

    public abstract void draw();
}
