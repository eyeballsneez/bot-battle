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

        fill(color(0, 0, 255));
        for (actor actor : game.team1Actors) {
            if (actor instanceof base) {
                strokeWeight(2);
                ellipse((float) actor.getX(), (float) actor.getY(), 60, 60);
            } else if (actor instanceof guy) {
                strokeWeight(0);
                ellipse((float) actor.getX(), (float) actor.getY(), 20, 20);
            }
        }

        fill(color(255, 0, 0));
        for (actor actor : game.team2Actors) {
            if (actor instanceof base) {
                strokeWeight(10);
                ellipse((float) actor.getX(), (float) actor.getY(), 30, 30);
            } else if (actor instanceof guy) {
                strokeWeight(0);
                ellipse((float) actor.getX(), (float) actor.getY(), 10, 10);
            }
        }
    }
}
