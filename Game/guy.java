package Game;

import java.lang.Math;
import java.util.ArrayList;

public abstract class guy extends actor {
    protected double direction;
    private double speed;
    private double maxSpeed;
    private double hp;
    private double maxHp;
    private double size;

    public guy(double startx, double starty, double startMaxSpeed, double size, double maxHP, ArrayList<actor> allies, ArrayList<actor> enemies, Game game) {
        super(allies, enemies, game);
        x = startx;
        y = starty;
        maxSpeed = startMaxSpeed;
        hp = maxHP;
        this.maxHp = maxHP;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    protected void move() {
        x += Math.cos(direction) * speed;
        y += Math.sin(direction) * speed;

        x = Math.max(x, 0);
        x = Math.min(x, 800);
        y = Math.max(y, 0);
        y = Math.min(y, 800);

        for (int i = 0; i < allies.size(); i++) {
            actor ally = allies.get(i);
            double dist = Math.sqrt((y - ally.getY()) * (y - ally.getY()) + (x - ally.getX()) * (x - ally.getX()));

            if ((ally instanceof guy) && ally != this) {
                if (dist < size/4)
                {
                    x -= Math.sin(direction) * speed;
                    y -= Math.cos(direction) * speed;
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            actor enemy = enemies.get(i);
            double dist = Math.sqrt((y - enemy.getY()) * (y - enemy.getY()) + (x - enemy.getX()) * (x - enemy.getX()));

            if ((enemy instanceof guy)) {
                if (dist < size/4)
                {
                    x -= Math.sin(direction) * speed;
                    y -= Math.cos(direction) * speed;
                }
            }
        }

    }

    public void damage(double dmg){
        hp -= dmg;
        if (hp <= 0) {
            game.remove(this);
        }
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setSpeed(double speed) {
        if (Math.abs(speed) <= maxSpeed) {
            this.speed = speed;
        }
    }

    public double getHp() {
        return hp;
    }
    public double getMaxHp() {
        return maxHp;
    }

    public double getDirection() {
        return direction;
    }
    public double getSpeed() {
        return speed;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
}
