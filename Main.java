import processing.core.*;

import java.time.LocalDateTime;

public class Main {

    /*      SETTINGS        */
    static boolean VISUALIZING = true;
    static final int TARGET_FPS = 60;

    public static void main(String[] args) {

        boolean gameRunning = true;
        Game game = new Game();
        base base = new base(50, 1, 400, 400, game.team1Actors, game.team2Actors);
        game.team1Actors.add(base);
        base.spawnInfantryGuy();

        GameView gameView = null;

        if (VISUALIZING) {
            gameView = new GameView(game);
            PApplet.runSketch(new String[]{"GameView"}, gameView);
        }

        long now;
        long updateTime;
        long wait;

        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        if (!VISUALIZING) {
            while (gameRunning) {
                game.tick();
            }
        } else {
            while (gameRunning) {
                game.tick();

                gameView.redraw();

                now = System.nanoTime();
                updateTime = System.nanoTime() - now;
                wait = (OPTIMAL_TIME - updateTime) / 1000000;

                infantryGuy infantry = (infantryGuy) game.team1Actors.get(1);
                infantry.setSpeed(4);
                infantry.setDirection((double) LocalDateTime.now().getNano() / 1000000000 * 2 * Math.PI);
                infantry.shoot();

                try {
                    Thread.sleep(wait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}