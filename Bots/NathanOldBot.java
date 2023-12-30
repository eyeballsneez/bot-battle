package Bots;

import Game.*;

import java.util.ArrayList;

// Create a new bot class by extending the Bot class
public class NathanOldBot extends Bot {
    /*  Bots are exposed an ArrayList of allies and an ArrayList of enemies  */
    /*  Your constructor should call super as follows:   */
    public NathanOldBot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
    }

    /*  AVAILABLE METHODS   */
    /*  actor.getX() and Game.actor.getY()  - returns the x and y coordinates of the Game.actor
    /*  base.invest(int amount)             - removes amount from your base's money, but increases your income by amount / 100
    /*  base.spawnInfantryGuy()             - spawns an infantryGuy at the location of your base, if your base has enough money
    /*  guy.setDirection(double direction)  - points the guy in the desired direction
                                                (in radians)
    /*  guy.setSpeed(double speed)          - sets the guy's movement speed
    /*  guy.getDirection()                  - returns the guy's direction
    /*  guy.getSpeed()                      - returns the guy's speed
    /*  guy.getMaxSpeed()                   - returns the guy's maximum speed
    /*  infantryGuy.shoot()                 - shoots a projectile in the direction the infantryGuy is facing
                                                (shoots after cooldown time elapsed, limited range and projectile speed)
    */

    /*  tick() is called every game tick   */
    @Override
    public void tick() {
        for (actor ally : allies) {
            if (ally instanceof base base) {
                performBaseAction(base);
            } else if (ally instanceof guy guy) {
                performGuyAction(guy);
            }
        }
    }
    private void performBaseAction(base base) {
        if (base.getMoney() > 500 * base.getIncome()) {
            base.invest((int) (base.getMoney() * 0.9));
        }

        if (allies.size() - 1 < 10 * base.getIncome()) {
            base.spawnInfantryGuy();
        }
    }
    private void performGuyAction(guy guy) {
        double closestEnemyDistance = Double.MAX_VALUE;
        guy closestEnemy = null;

        double closestProjectileDistance = Double.MAX_VALUE;
        projectile closestProjectile = null;

        for (actor enemy : enemies) {
            if (enemy instanceof projectile projectile) {
                double dist = distance(guy, enemy);

                if (dist < closestProjectileDistance) {
                    closestProjectile = projectile;
                    closestProjectileDistance = dist;
                }
            } else if (enemy instanceof guy) {
                double dist = distance(guy, enemy);

                if (dist < closestEnemyDistance) {
                    closestEnemy = (guy) enemy;
                    closestEnemyDistance = dist;
                }
            }
        }


        if (guy instanceof infantryGuy infantry && infantry.getGunCooldown() <= 0 && closestEnemy != null) {
            guy.setDirection(projectileAngle(closestEnemy, guy));

            if (closestEnemyDistance <= 290) guy.setSpeed(-guy.getMaxSpeed());
            else guy.setSpeed(guy.getMaxSpeed());

            infantry.shoot();
        } else if (closestEnemy == null) {
            guy.setDirection(angleBetween(enemies.get(0), guy));
            if (distance(enemies.get(0), guy) <= 290) {
                guy.setSpeed(-guy.getMaxSpeed());
            } else {
                guy.setSpeed(guy.getMaxSpeed());
            }
        } else {
            guy.setDirection(projectileAngle(closestEnemy, guy));

            if (closestEnemyDistance <= 290) guy.setSpeed(-guy.getMaxSpeed());
            else guy.setSpeed(guy.getMaxSpeed());
        }
    }

    private double projectileAngle(guy enemy, actor ally) {
        double evx = Math.cos(enemy.getDirection()) * enemy.getSpeed();
        double evy = Math.sin(enemy.getDirection()) * enemy.getSpeed();
        double vp = 10;
        double dx = enemy.getX() - ally.getX();
        double dy = enemy.getY() - ally.getY();

        double a = (evx * evx) + (evy * evy) - (vp * vp);
        double b = 2 * (evx * dx + evy * dy);
        double c = dx * dx + dy * dy;

        double t = (-b - Math.sqrt(b * b - (4 * a * c))) / (2 * a);

        double predictedDx = t * evx + dx;
        double predictedDy = t * evy + dy;

        return Math.atan2(predictedDy, predictedDx);
    }

    private double distance(actor a, actor b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double angleBetween(double toX, double toY, double fromX, double fromY) {
        double dx = toX - fromX;
        double dy = toY - fromY;
        return Math.atan2(dy, dx);
    }
    private double angleBetween(actor to, actor from) {
        double dx = to.getX() - from.getX();
        double dy = to.getY() - from.getY();
        return Math.atan2(dy, dx);
    }
}
