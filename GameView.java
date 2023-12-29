import processing.core.PApplet;

public class GameView extends PApplet {
    Game game;

    public GameView(Game game) {
        super();
        this.game = game;
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        noLoop();
    }

    public void drawBase(base base, int team) {
        if (team == 1) {
            fill(color(0, 0, 150));
        } else if (team == 2) {
            fill(color(150, 0, 0));
        }

        float x = (float) base.getX();
        float y = (float) base.getY();

        strokeWeight(1);
        ellipse(x, y, 60, 60);
        int money = base.getMoney();
        textAlign(CENTER);
        fill(0);
        text(money, x, y + 50);
    }

    public void drawGuy(guy guy, int team) {
        if (team == 1) {
            fill(color(20, 50, 255));
        } else if (team == 2) {
            fill(color(255, 50, 20));
        }

        float x = (float) guy.getX();
        float y = (float) guy.getY();

        strokeWeight(0);
        ellipse(x, y, 20, 20);

        fill(color(255, 0, 0));
        int halfWidth = 15;
        int thickness = 5;
        rect(x - halfWidth, y + 25, 2 * halfWidth, thickness);
        fill(color(0, 255, 0));
        rect(x - halfWidth, y + 25, (2 * halfWidth) * (float) (guy.getHp() / guy.getMaxHp()), thickness);
    }

    public void drawProjectile(projectile projectile, int team) {
        if (team == 1) {
            fill(color(0, 0, 255));
        } else if (team == 2) {
            fill(color(255, 0, 0));
        }

        ellipse((float) projectile.getX(), (float) projectile.getY(), (float) projectile.getSize(), (float) projectile.getSize());
    }

    public void draw() {
        background(255);

        for (int i = 0; i < game.team1Actors.size(); i++) {
            actor actor = game.team1Actors.get(i);

            if (actor instanceof base) {
                drawBase((base) actor, 1);
            } else if (actor instanceof guy) {
                drawGuy((guy) actor, 1);
            } else if (actor instanceof projectile) {
                drawProjectile((projectile) actor, 1);
            }
        }

        for (int i = 0; i < game.team2Actors.size(); i++) {
            actor actor = game.team2Actors.get(i);

            if (actor instanceof base) {
                drawBase((base) actor, 2);
            } else if (actor instanceof guy) {
                drawGuy((guy) actor, 2);
                System.out.println(((guy) actor).getHp());
            } else if (actor instanceof projectile) {
                drawProjectile((projectile) actor, 2);
            }
        }
    }
}
