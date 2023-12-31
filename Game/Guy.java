package Game;

import java.lang.Math;
import java.util.ArrayList;

public abstract class Guy extends Actor {
    protected double direction;
    private double speed;
    private double maxSpeed;
    private double hp;
    private double maxHp;
    private double size;

    public Guy(double startx, double starty, double startMaxSpeed, double size, double maxHP, ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
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
        x = Math.min(x, 3200);
        y = Math.max(y, 0);
        y = Math.min(y, 3200);

        for (int i = 0; i < allies.size(); i++) {
            Actor ally = allies.get(i);
            double dist = Math.sqrt((y - ally.getY()) * (y - ally.getY()) + (x - ally.getX()) * (x - ally.getX()));

            if ((ally instanceof Guy) && ally != this) {
                if (dist < size/4)
                {
                    x -= Math.sin(direction) * speed;
                    y -= Math.cos(direction) * speed;
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Actor enemy = enemies.get(i);
            double dist = Math.sqrt((y - enemy.getY()) * (y - enemy.getY()) + (x - enemy.getX()) * (x - enemy.getX()));

            if ((enemy instanceof Guy)) {
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
