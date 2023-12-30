import java.util.ArrayList;

public class DroneBot extends Bot {

    public DroneBot(ArrayList<actor> allies, ArrayList<actor> enemies) {
        super(allies, enemies);
    }

    @Override
    public void tick() {
        for (actor ally : allies) {
            if (ally instanceof base) {
                ((base) ally).spawnInfantryGuy();
            } else if (ally instanceof guy) {
                guy guy = (guy) ally;

                double closestDistance = Double.MAX_VALUE;
                double angle = 100;

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

                if (angle == 100) {
                    guy.setSpeed(0);
                    continue;
                }

                guy.setDirection(angle);
                guy.setSpeed(guy.getMaxSpeed() / 2);
                if (guy instanceof infantryGuy) {
                    ((infantryGuy) guy).shoot();
                }
            }
        }
    }
}
