package Bots;

import java.util.ArrayList;
import Game.*;

// Create a new bot class by extending the Bots.Bot class
public class DemoBot extends Bot {
    /*  Bots are exposed an ArrayList of allies and an ArrayList of enemies  */
    /*  Your constructor should call super as follows:   */
    public DemoBot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
    }

    /*  AVAILABLE METHODS   */
    /*  Game.actor.getX() and Game.actor.getY()        - returns the x and y coordinates of the Game.actor
    /*  Game.base.invest(int amount)             - removes amount from your Game.base's money, but increases your income by amount / 100
    /*  Game.base.spawnInfantryGuy()             - spawns an Game.infantryGuy at the location of your Game.base, if your Game.base has enough money
    /*  Game.guy.setDirection(double direction)  - points the Game.guy in the desired direction
                                                (in radians)
    /*  Game.guy.setSpeed(double speed)          - sets the Game.guy's movement speed
    /*  Game.guy.getDirection()                  - returns the Game.guy's direction
    /*  Game.guy.getSpeed()                      - returns the Game.guy's speed
    /*  Game.guy.getMaxSpeed()                   - returns the Game.guy's maximum speed
    /*  Game.infantryGuy.shoot()                 - shoots a Game.projectile in the direction the Game.infantryGuy is facing
                                                (shoots after cooldown time elapsed, limited range and Game.projectile speed)
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
        if (base.getMoney() > 50) {
            if (allies.size() - 1 > 10) {
                base.invest(base.getMoney());
            } else {
                base.spawnInfantryGuy();
            }
        }
    }
    private void performGuyAction(guy guy) {
        double angle = getAngleToClosestEnemy(guy);
        if (angle == -1) return;  // hacky way of checking if there were no enemies to attack

        guy.setDirection(angle);
        guy.setSpeed(guy.getMaxSpeed() / 2);

        if (guy instanceof infantryGuy) ((infantryGuy) guy).shoot();
    }
    private double getAngleToClosestEnemy(actor ally) {
        double closestDistance = Double.MAX_VALUE;
        double angle = -1;

        for (actor enemy : enemies) {
            if (!(enemy instanceof guy)) continue;
            double dx = enemy.getX() - ally.getX();
            double dy = enemy.getY() - ally.getY();
            double dist = dx * dx + dy * dy;
            if (dist < closestDistance) {
                closestDistance = dist;
                angle = Math.atan2(dx, dy);
            }
        }

        return angle;
    }
}
