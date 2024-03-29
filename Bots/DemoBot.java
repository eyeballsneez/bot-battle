package Bots;

import java.util.ArrayList;
import Game.*;

// Create a new bot class by extending the Bot class
public class DemoBot extends Bot {
    /*  Bots are exposed an ArrayList of allies and an ArrayList of enemies  */
    /*  Your constructor should call super as follows:   */
    public DemoBot(ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(allies, enemies, game);
    }

    /*  AVAILABLE METHODS   */
    /*  actor.getX() and Game.actor.getY()  - returns the x and y coordinates of the Game.actor
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
        for (Actor ally : allies) {
            if (ally instanceof Base base) {
                performBaseAction(base);
            } else if (ally instanceof Guy guy) {
                performGuyAction(guy);
            }
        }
    }
    private void performBaseAction(Base base) {
        if (base.getMoney() > 150) {
            if (allies.size() - 1 > 10) {
                base.spawnMineGuy();
            } else {
                base.spawnInfantryGuy();
            }
        }
    }
    private void performGuyAction(Guy guy) {
        double angle = getAngleToClosestEnemy(guy);
        if (angle == -1) return;  // hacky way of checking if there were no enemies to attack

        guy.setDirection(angle);
        guy.setSpeed(guy.getMaxSpeed() / 2);

        if (guy instanceof InfantryGuy) ((InfantryGuy) guy).shoot();
    }
    private double getAngleToClosestEnemy(Actor ally) {
        double closestDistance = Double.MAX_VALUE;
        double angle = -1;

        for (Actor enemy : enemies) {
            if (!(enemy instanceof Guy)) continue;
            double dx = enemy.getX() - ally.getX();
            double dy = enemy.getY() - ally.getY();
            double dist = dx * dx + dy * dy;
            if (dist < closestDistance) {
                closestDistance = dist;
                angle = Math.atan2(dy, dx);
            }
        }

        return angle;
    }
}
