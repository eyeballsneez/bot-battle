import java.util.ArrayList;

public class Game {
    ArrayList<actor> team1Actors;
    ArrayList<actor> team2Actors;

    public Game() {
        team1Actors = new ArrayList<>();
        team2Actors = new ArrayList<>();
    }

    public void tick() {
        for (actor actor : team1Actors) {
            actor.tick();
        }

        for (actor actor : team2Actors) {
            actor.tick();
        }
    }
}
