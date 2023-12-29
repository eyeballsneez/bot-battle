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
    public void draw() {
        background(255);

        for (int i = 0; i < game.team1Actors.size(); i++) {
            actor actor = game.team1Actors.get(i);

            float x = (float) actor.getX();
            float y = (float) actor.getY();

            if (actor instanceof base) {
                fill(color(0, 0, 150));
                strokeWeight(1);
                ellipse(x, y, 60, 60);
                int money = ((base) actor).getMoney();
                textAlign(CENTER);
                fill(0);
                text(money, x, y + 50);
            } else if (actor instanceof guy) {
                fill(color(20, 50, 255));
                strokeWeight(0);
                ellipse(x, y, 20, 20);
            } else if (actor instanceof projectile) {
                fill(color(0, 0, 255));
                pushMatrix();
                translate(x, y);
                rotate(
                        (float) -((projectile) actor).getDirection()
                );
                rect(0, 0, 5, 10);
                popMatrix();
            }
        }

        fill(color(255, 0, 0));
        for (int i = 0; i < game.team2Actors.size(); i++) {
            actor actor = game.team2Actors.get(i);
            if (actor instanceof base) {
                strokeWeight(1);
                ellipse((float) actor.getX(), (float) actor.getY(), 30, 30);
            } else if (actor instanceof guy) {
                strokeWeight(0);
                ellipse((float) actor.getX(), (float) actor.getY(), 10, 10);
            }
        }
    }
}
