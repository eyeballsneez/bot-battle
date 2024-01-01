package Game;

import java.util.ArrayList;

public class Projectile extends Actor {
    private double speed, direction, dmg, size;
    private int lifespan;
    public Projectile(double startx, double starty, double initspeed, double initdirection, double setdmg, double size, int lifespan, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game){
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
            Actor enemy = enemies.get(i);
            if (enemy instanceof Damageable obj) {
                double dist = Math.sqrt((y - enemy.getY()) * (y - enemy.getY()) + (x - enemy.getX()) * (x - enemy.getX()));

                if (dist < (size + obj.getSize())/2) {
                    obj.damage(dmg);
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
