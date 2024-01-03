package Bots;

import Game.*;

import java.util.ArrayList;

// Create a new bot class by extending the Bot class
public class NathanBBot extends Bot {
    /*  Bots are exposed an ArrayList of allies and an ArrayList of enemies  */
    /*  Your constructor should call super as follows:   */

    int minerCount, infantryCount, income;
    int time;

    public NathanBBot(ArrayList<Actor> allies, ArrayList<Actor> enemies, Game game) {
        super(allies, enemies, game);

        time = 0;

        minerCount = 0;
        infantryCount = 0;
        income = 0;
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
        time++;

        minerCount = 0;
        infantryCount = 0;

        for (Actor ally : allies) {
            if (ally instanceof MinerGuy miner) {
                minerCount++;

                if (miner.getSpeed() == 0) {
                    income += miner.getIncome();
                }
            } else if (ally instanceof InfantryGuy) {
                infantryCount++;
            }
        }

        for (Actor ally : allies) {
            if (ally instanceof Base base) {
                performBaseAction(base);
            } else if (ally instanceof InfantryGuy infantry) {
                performInfantryAction(infantry);
            } else if (ally instanceof MinerGuy MinerGuy) {
                performMinerAction(MinerGuy);
            }
        }
    }
    private void performBaseAction(Base base) {
        if (minerCount < 6 || income < infantryCount * 300) {
            if (base.getMoney() >= 500) {
                base.spawnMineGuy();
            }
        } else {
            for (int i = 0; i < base.getMoney() / 150 + 1; i++) {
                base.spawnInfantryGuy();
            }
        }
    }

    private void performMinerAction(MinerGuy MinerGuy) {
        double TRX = 3200;
        double TRY = 0;
        double BLX = 0;
        double BLY = 3200;

        double distToTR = distance(TRX, TRY, MinerGuy.getX(), MinerGuy.getY());
        double distToBL = distance(BLX, BLY, MinerGuy.getX(), MinerGuy.getY());

        if (Math.min(distToTR, distToBL) < MinerGuy.getMaxSpeed() * 2) {
            MinerGuy.setSpeed(0);
            return;
        }

        if (distToTR < distToBL) {
            MinerGuy.setDirection(angleBetween(TRX, TRY, MinerGuy.getX(), MinerGuy.getY()));
        } else {
            MinerGuy.setDirection(angleBetween(BLX, BLY, MinerGuy.getX(), MinerGuy.getY()));
        }

        if ((time - 3) % 80 == 0) {
            MinerGuy.setSpeed(MinerGuy.getMaxSpeed());
        } else {
            MinerGuy.setSpeed(0);
        }
    }

    private void performInfantryAction(InfantryGuy infantry) {
        double closestInfantryDistance = Double.MAX_VALUE;
        InfantryGuy closestInfantry = null;

        double closestProjectileDistance = Double.MAX_VALUE;
        Projectile closestProjectile = null;

        double closestMinerDistance = Double.MAX_VALUE;
        MinerGuy closestMiner = null;

        for (Actor enemy : enemies) {
            double dist = distance(infantry, enemy);

            if (enemy instanceof Projectile projectile) {
                if (dist < closestProjectileDistance) {
                    closestProjectile = projectile;
                    closestProjectileDistance = dist;
                }
            }  else if (enemy instanceof MinerGuy minerGuy) {
                if (dist < closestMinerDistance) {
                    closestMiner = minerGuy;
                    closestMinerDistance = dist;
                }
            } else if (enemy instanceof InfantryGuy guy) {
                if (dist < closestInfantryDistance) {
                    closestInfantry = guy;
                    closestInfantryDistance = dist;
                }
            }
        }

        // Shooting
        if (infantry.getGunCooldown() <= 0) {
            if (closestInfantry == null && closestMiner == null) {
                shootEnemyBase(infantry);
            } else if (closestInfantryDistance < closestMinerDistance) {
                shootAt(closestInfantry, infantry);
            } else {
                shootAt(closestMiner, infantry);
            }
        }

        // Movement
        if (closestProjectile != null && closestProjectileDistance < 300) {
            dodge(closestProjectile, infantry);
        } else if (closestMiner != null && (closestInfantry == null || closestInfantryDistance > closestMinerDistance)) {
            intercept(closestMiner, infantry);
        } else if (closestInfantry != null) {
            intercept(closestInfantry, infantry);
        } else {
            goToEnemyBase(infantry);
        }
    }

    private void goToEnemyBase(Guy guy) {
        guy.setDirection(angleBetween(enemies.get(0), guy));
        if (distance(enemies.get(0), guy) <= 290) {
            guy.setSpeed(-guy.getMaxSpeed());
        } else {
            guy.setSpeed(guy.getMaxSpeed());
        }
    }

    private void intercept(Guy enemy, Guy ally) {
        ally.setDirection(projectileAngle(enemy, ally));
        double distance = distance(enemy, ally);

        if (distance <= 290) ally.setSpeed(-ally.getMaxSpeed());
        else ally.setSpeed(ally.getMaxSpeed());
    }

    private void shootEnemyBase(InfantryGuy infantry) {
        Base enemyBase = (Base) enemies.get(0);
        infantry.setDirection(angleBetween(enemyBase, infantry));
        infantry.shoot();
    }

    private void shootAt(Guy enemy, InfantryGuy infantry) {
        infantry.setDirection(projectileAngle(enemy, infantry));

        double distance = distance(infantry, enemy);

        if (distance <= 290) infantry.setSpeed(-infantry.getMaxSpeed());
        else infantry.setSpeed(infantry.getMaxSpeed());

        infantry.shoot();
    }

    private void dodge(Projectile projectile, Guy guy) {
        double angleFromProjectile = angleBetween(guy, projectile);
        double distance = distance(projectile, guy);

        if (Math.abs(angleFromProjectile - projectile.getDirection()) < Math.PI / distance * 2) {
            double cw = projectile.getDirection() - Math.PI / 4;
            double ccw = projectile.getDirection() + Math.PI / 4;

            double dcw = cw - angleFromProjectile;
            double dccw = ccw - angleFromProjectile;

            double direction = ccw;
            if (dcw < dccw) {
                direction = cw;
            }
            guy.setDirection(direction);
            guy.setSpeed(guy.getMaxSpeed());
        }
    }

    private double[] cartesian(double angle, double magnitude) {
        return new double[]{
                Math.cos(angle) * magnitude,
                Math.sin(angle) * magnitude
        };
    }

    private double projectileAngle(Guy enemy, Actor ally) {
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

    private double distance(Actor a, Actor b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double distance(double aX, double aY, double bX, double bY) {
        double dx = aX - bX;
        double dy = aY - bY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double angleBetween(double toX, double toY, double fromX, double fromY) {
        double dx = toX - fromX;
        double dy = toY - fromY;
        return Math.atan2(dy, dx);
    }
    private double angleBetween(Actor to, Actor from) {
        double dx = to.getX() - from.getX();
        double dy = to.getY() - from.getY();
        return Math.atan2(dy, dx);
    }
}
