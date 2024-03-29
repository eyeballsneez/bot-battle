import Bots.*;
import Game.*;
import processing.core.*;

public class Main {

    /*      SETTINGS        */
    static boolean VISUALIZING = true;
    static final int TARGET_FPS = 120;

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

        int winner = 0;

        Game game = startNewGame();
        GameView gameView = null;

        Bot team1Bot = new NathanBot(game.team1Actors, game.team2Actors, game);
        Bot team2Bot = new NathanBot(game.team2Actors, game.team1Actors, game);

        if (VISUALIZING) {
            gameView = new GameView(game);
            PApplet.runSketch(new String[]{"GameView"}, gameView);
        }

        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        if (!VISUALIZING) {
            System.out.println("Started Game");
            int tick = 0;

            while (gameRunning) {
                tick++;
                team1Bot.tick();
                team2Bot.tick();
                game.tick();

                if (game.checkWinner() != 0) {
                    gameRunning = false;
                    winner = game.checkWinner();
                }

                if (tick % 1000 == 0) {
                    System.out.println("Running Simulation...");
                }
            }
        } else {
            while (gameRunning) {
                team1Bot.tick();
                team2Bot.tick();
                game.tick();

                if (game.checkWinner() != 0) {
                    gameRunning = false;
                    winner = game.checkWinner();
                }

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

        switch (winner) {
            case 1:
                System.out.println("Team 1 wins!");
                break;
            case 2:
                System.out.println("Team 2 wins!");
                break;
            default:
                System.out.println("Tie!");
                break;
        }
    }
}