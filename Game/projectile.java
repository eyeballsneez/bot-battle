package Game;

import java.util.ArrayList;

public class projectile extends actor {
    private double speed, direction, dmg, size;
    private int lifespan;
    public projectile(double startx, double starty, double initspeed, double initdirection, double setdmg, double size, int lifespan, ArrayList<actor> allies, ArrayList<actor> enemies, Game game){
        super(allies, enemies, game);
        x = startx;
        y = starty;
        speed = initspeed;
        direction = initdirection;
        dmg = setdmg;
        this.size = size;

        this.lifespan = lifespan;
    }

    public void tick() {
        lifespan -= 1;

        this.x += Math.cos(direction) * speed;
        this.y += Math.sin(direction) * speed;

        for (int i = 0; i < enemies.size(); i++) {
            actor enemy = enemies.get(i);
            if (enemy instanceof guy) {
                guy enemyGuy = (guy) enemy;

                double dist = Math.sqrt((y - enemy.getY()) * (y - enemy.getY()) + (x - enemy.getX()) * (x - enemy.getX()));

                if (dist < (size + enemyGuy.getSize())/2) {
                    enemyGuy.damage(dmg);
                    game.remove(this);
                }
            }
        }

        if (lifespan <= 0) game.remove(this);
    }

    public double getDirection() {
        return direction;
    }
    public double getSize() {
        return size;
    }

    public double getSpeed() {
        return speed;
    }
}
