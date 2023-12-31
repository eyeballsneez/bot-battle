import Game.*;
import processing.core.PApplet;
import processing.event.*;

import java.util.ArrayList;

public class GameView extends PApplet {
    Game game;

    int cameraX, cameraY;
    float zoom;

    boolean leftPressed;
    boolean rightPressed;
    boolean upPressed;
    boolean downPressed;

    int zoomCount;

    public GameView(Game game) {
        super();
        this.game = game;
        cameraX = 400;
        cameraY = 400;
        zoom = 1;

        leftPressed = false;
        rightPressed = false;
        upPressed = false;
        downPressed = false;
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
        if (guy instanceof mineGuy){
            rect(x, y, (float) guy.getSize(), (float) guy.getSize(), (float) guy.getDirection());
        } else {
            ellipse(x, y, (float) guy.getSize(), (float) guy.getSize());
        }

        fill(color(255, 0, 0));
        int halfWidth = 15;
        int thickness = 5;
        rect(x - halfWidth, y + 25, 2 * halfWidth, thickness);
        fill(color(0, 255, 0));
        rect(x - halfWidth, y + 25, (2 * halfWidth) * (float) (guy.getHp() / guy.getMaxHp()), thickness);
        strokeWeight(2);
        line(x, y,  ((float) (x+(Math.cos(guy.getDirection())*(guy.getSize()/2)))), ((float) (y+(Math.sin(guy.getDirection())*(guy.getSize()/2)))));
        strokeWeight(0);
    }
    public void drawProjectile(projectile projectile, int team) {
        if (team == 1) {
            fill(color(0, 0, 255));
        } else if (team == 2) {
            fill(color(255, 0, 0));
        }

        ellipse((float) projectile.getX(), (float) projectile.getY(), (float) projectile.getSize(), (float) projectile.getSize());
    }

    public void mouseWheel(MouseEvent event) {
        float amount = -event.getCount();

        cameraX += mouseX;
        cameraY += mouseY;
        float delta = (float) (amount > 0 ? 1.05 : amount < 0 ? 1.0/1.05 : 1.0);
        zoom *= delta;
        cameraX *= delta;
        cameraY *= delta;
        cameraX -= mouseX;
        cameraY -= mouseY;
    }

    public void mouseDragged() {
        float dx = mouseX - pmouseX;
        float dy = mouseY - pmouseY;
        cameraX -= dx;
        cameraY -= dy;
    }

    public void keyPressed() {
        if (key == CODED) {
            switch(keyCode) {
                case LEFT:
                    leftPressed = true;
                    break;
                case RIGHT:
                    rightPressed = true;
                    break;
                case UP:
                    upPressed = true;
                    break;
                case DOWN:
                    downPressed = true;
                    break;
            }
        }
    }

    public void keyReleased() {
        if (key == CODED) {
            switch(keyCode) {
                case LEFT:
                    leftPressed = false;
                    break;
                case RIGHT:
                    rightPressed = false;
                    break;
                case UP:
                    upPressed = false;
                    break;
                case DOWN:
                    downPressed = false;
                    break;
            }
        }
    }

    public void draw() {
        background(255);

        pushMatrix();

        textAlign(LEFT);
        text(cameraX + ", " + cameraY, 2, 10);

        if (leftPressed) {
            cameraX -= 10;
        }
        if (rightPressed) {
            cameraX += 10;
        }
        if (upPressed) {
            cameraY -= 10;
        }
        if (downPressed) {
            cameraY += 10;
        }

        translate(-cameraX, -cameraY);
        scale(zoom);

        drawBase((base) game.team1Actors.get(0), 1);
        drawBase((base) game.team2Actors.get(0), 2);

        for (int i = 1; i < game.team1Actors.size(); i++) {
            actor actor = game.team1Actors.get(i);
            if (actor instanceof base) {
                drawBase((base) actor, 1);
            } else if (actor instanceof guy) {
                drawGuy((guy) actor, 1);
            } else if (actor instanceof projectile) {
                drawProjectile((projectile) actor, 1);
            }
        }

        for (int i = 1; i < game.team2Actors.size(); i++) {
            actor actor = game.team2Actors.get(i);
            if (actor instanceof base) {
                drawBase((base) actor, 2);
            } else if (actor instanceof guy) {
                drawGuy((guy) actor, 2);
            } else if (actor instanceof projectile) {
                drawProjectile((projectile) actor, 2);
            }
        }

        popMatrix();
    }
}
