import java.util.ArrayList;

public class projectile extends actor {
    private double speed, direction, dmg, size;
    public projectile(double startx, double starty, double initspeed, double initdirection, double setdmg, double size, ArrayList<actor> allies, ArrayList<actor> enemies, Game game){
        super(allies, enemies, game);
        x = startx;
        y = starty;
        speed = initspeed;
        direction = initdirection;
        dmg = setdmg;
        this.size = size;
    }

    public void tick() {
        this.x += Math.sin(direction) * speed;
        this.y += Math.cos(direction) * speed;

        for (int i = 0; i < enemies.size(); i++) {
            actor enemy = enemies.get(i);
            if (enemy instanceof guy) {
                guy enemyGuy = (guy) enemy;

                double dist = Math.sqrt((y - enemy.getY()) * (y - enemy.getY()) + (x - enemy.getX()) * (x - enemy.getX()));

                if (dist < size) {
                    enemyGuy.damage(dmg);
                    game.remove(this);
                }
            }
        }
    }

    public double getDirection() {
        return direction;
    }
    public double getSize() {
        return size;
    }
}
