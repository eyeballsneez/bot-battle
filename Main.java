import processing.core.*;

import java.time.LocalDateTime;

public class Main {

    /*      SETTINGS        */
    static boolean VISUALIZING = true;
    static final int TARGET_FPS = 60;

    public static void main(String[] args) {

        boolean gameRunning = true;
        Game game = new Game();

        base base1 = new base(50, 1, 600, 600, game.team1Actors, game.team2Actors, game);
        game.add(base1, game.team1Actors);
        //base1.spawnInfantryGuy();

        base base2 = new base(50, 1, 300, 300, game.team2Actors, game.team1Actors, game);
        game.team2Actors.add(base2);
        base2.spawnInfantryGuy();

        GameView gameView = null;

        Bot team1Bot = new DroneBot(game.team1Actors, game.team2Actors);

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
                team1Bot.tick();

                gameView.redraw();

                now = System.nanoTime();
                updateTime = System.nanoTime() - now;
                wait = (OPTIMAL_TIME - updateTime) / 1000000;

                if (game.team2Actors.get(0) instanceof base) {
                    base base = (base) game.team2Actors.get(0);

                    if (game.team2Actors.size() < 4) base.spawnInfantryGuy();
                }

                for (actor actor : game.team2Actors) {
                    if (!(actor instanceof guy)) continue;
                    infantryGuy infantry = (infantryGuy) actor;
                    infantry.setSpeed(4);
                    infantry.setDirection(infantry.getDirection() + (Math.random() - 0.5) * Math.PI);
                }

                /*
                infantryGuy infantry = (infantryGuy) game.team1Actors.get(1);
                infantry.setSpeed(4);
                infantry.setDirection((double) LocalDateTime.now().getNano() / 1000000000 * 2 * Math.PI);
                infantry.shoot();
                 */

                try {
                    Thread.sleep(wait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}