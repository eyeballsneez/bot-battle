import Game.*;
import processing.core.PApplet;
import processing.event.*;

public class GameView extends PApplet {
    Game game;

    int cameraX, cameraY;
    float zoom;

    boolean leftPressed;
    boolean rightPressed;
    boolean upPressed;
    boolean downPressed;

    public GameView(Game game) {
        super();
        this.game = game;
        cameraX = 400;
        cameraY = 400;
        zoom = 0.5f;

        leftPressed = false;
        rightPressed = false;
        upPressed = false;
        downPressed = false;
    }
    public void settings() {
        size(1700, 950);
    }
    public void setup() {
        noLoop();
    }

    private void drawBorder() {
        stroke(0);
        fill(255);
        rect(0, 0, 3200, 3200);
    }

    private void drawBase(Base base, int team) {
        if (team == 1) {
            fill(color(0, 0, 150));
        } else if (team == 2) {
            fill(color(150, 0, 0));
        }

        float x = (float) base.getX();
        float y = (float) base.getY();

        strokeWeight(1);
        ellipse(x, y, 60, 60);

        drawHealthBar(base, x, y + 60);
        int money = base.getMoney();
        textAlign(CENTER);
        fill(0);
        text(money, x, y + 50);
    }
    private void drawGuy(Guy guy, int team) {
        if (team == 1) {
            fill(color(20, 50, 255));
        } else if (team == 2) {
            fill(color(255, 50, 20));
        }

        float x = (float) guy.getX();
        float y = (float) guy.getY();


        strokeWeight(0);
        if (guy instanceof MinerGuy){
            rect((float) (x - guy.getSize() / 2), (float) (y - guy.getSize() / 2), (float) guy.getSize(), (float) guy.getSize(), (float) guy.getDirection());
        } else {
            ellipse(x, y, (float) guy.getSize(), (float) guy.getSize());
        }

        drawHealthBar(guy, x, y + 25);

        strokeWeight(2);
        line(x, y,  ((float) (x+(Math.cos(guy.getDirection())*(guy.getSize()/2)))), ((float) (y+(Math.sin(guy.getDirection())*(guy.getSize()/2)))));
        strokeWeight(0);
    }
    private void drawProjectile(Projectile projectile, int team) {
        if (team == 1) {
            fill(color(0, 0, 255));
        } else if (team == 2) {
            fill(color(255, 0, 0));
        }

        ellipse((float) projectile.getX(), (float) projectile.getY(), (float) projectile.getSize(), (float) projectile.getSize());
    }

    private void drawHealthBar(Damageable obj, float x, float y) {
        fill(color(255, 0, 0));
        int halfWidth = (int) (Math.log10(obj.getMaxHp()) * 20);
        int thickness = 5;
        rect(x - halfWidth, y, 2 * halfWidth, thickness);
        fill(color(0, 255, 0));
        rect(x - halfWidth, y, (2 * halfWidth) * (float) (obj.getHp() / obj.getMaxHp()), thickness);
    }

    public void mouseWheel(MouseEvent event) {
        float amount = -event.getCount();

        cameraX += mouseX - width / 2;
        cameraY += mouseY - height / 2;
        float delta = (float) (amount > 0 ? 1.05 : amount < 0 ? 1.0/1.05 : 1.0);
        zoom *= delta;
        cameraX *= delta;
        cameraY *= delta;
        cameraX -= mouseX - width / 2;
        cameraY -= mouseY - height / 2;
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

        if (leftPressed) cameraX -= 10;
        if (rightPressed) cameraX += 10;
        if (upPressed) cameraY -= 10;
        if (downPressed) cameraY += 10;

        pushMatrix();

        translate(-cameraX + width / 2, -cameraY + height / 2);
        scale(zoom);

        drawBorder();

        drawBase((Base) game.team1Actors.get(0), 1);
        drawBase((Base) game.team2Actors.get(0), 2);

        for (int i = 1; i < game.team1Actors.size(); i++) {
            Actor actor = game.team1Actors.get(i);

            if (actor instanceof Guy) {
                drawGuy((Guy) actor, 1);
            } else if (actor instanceof Projectile) {
                drawProjectile((Projectile) actor, 1);
            }
        }

        for (int i = 1; i < game.team2Actors.size(); i++) {
            Actor actor = game.team2Actors.get(i);

            if (actor instanceof Guy) {
                drawGuy((Guy) actor, 2);
            } else if (actor instanceof Projectile) {
                drawProjectile((Projectile) actor, 2);
            }
        }

        popMatrix();

        textAlign(LEFT);
        fill(0);
        text(cameraX + ", " + cameraY, 2, 10);
    }
}
