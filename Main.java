import Bots.*;
import Game.*;
import processing.core.*;

public class Main {

    /*      SETTINGS        */
    static boolean VISUALIZING = true;
    static final int TARGET_FPS = 60;

    public static Game startNewGame() {
        Game game = new Game();

        Base base1 = new Base(500, 3100, 3100, game.team1Actors, game.team2Actors, game);
        game.add(base1, game.team1Actors);

        Base base2 = new Base(500, 100, 100, game.team2Actors, game.team1Actors, game);
        game.add(base2, game.team2Actors);

        return game;
    }

    public static void main(String[] args) {

        boolean gameRunning = true;

        Game game = startNewGame();
        GameView gameView = null;

        Bot team1Bot = new NathanABot(game.team1Actors, game.team2Actors);
        Bot team2Bot = new NathanBot(game.team2Actors, game.team1Actors);

        if (VISUALIZING) {
            gameView = new GameView(game);
            PApplet.runSketch(new String[]{"GameView"}, gameView);
        }

        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        if (!VISUALIZING) {
            while (gameRunning) {
                team1Bot.tick();
                team2Bot.tick();
                game.tick();
            }
        } else {
            while (gameRunning) {
                team1Bot.tick();
                team2Bot.tick();
                game.tick();

                gameView.redraw();

                long now = System.nanoTime();
                long updateTime = System.nanoTime() - now;
                long wait = (OPTIMAL_TIME - updateTime) / 1000000;

                try {
                    Thread.sleep(wait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}