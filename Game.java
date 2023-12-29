import java.util.ArrayList;

public class Game {
    ArrayList<actor> team1Actors;
    ArrayList<actor> team2Actors;

    ArrayList<actor> removeQueue;

    public Game() {
        team1Actors = new ArrayList<>();
        team2Actors = new ArrayList<>();
        removeQueue = new ArrayList<>();
    }

    public void remove(actor actor) {
        removeQueue.add(actor);
    }

    public void tick() {
        // not using enhanced for-loop to prevent concurrent modification error
        for (actor team1Actor : team1Actors) {
            team1Actor.tick();
        }

        for (actor team2Actor : team2Actors) {
            team2Actor.tick();
        }

        // remove all the things that need to be deleted
        for (actor actor : removeQueue) {
            team1Actors.remove(actor);
            team2Actors.remove(actor);
        }
    }
}
