import processing.core.*;

public class Main {

    /*      SETTINGS        */
    static boolean VISUALIZING = true;
    static final int TARGET_FPS = 60;

    public static void main(String[] args) {

        boolean gameRunning = true;
        Game game = new Game();
        game.team1Actors.add(new base(0, 1, 400, 400, game.team1Actors, game.team2Actors));
        infantryGuy infantry = new infantryGuy(300, 300, game.team1Actors, game.team2Actors);
        infantry.setSpeed(3);
        game.team1Actors.add(infantry);

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

                try {
                    Thread.sleep(wait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}