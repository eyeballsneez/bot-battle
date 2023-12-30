package Game;

import java.util.ArrayList;

public class Game {
    public ArrayList<actor> team1Actors;
    public ArrayList<actor> team2Actors;
    ArrayList<actor> addTeam1Queue;
    ArrayList<actor> addTeam2Queue;
    ArrayList<actor> removeQueue;

    public Game() {
        team1Actors = new ArrayList<>();
        team2Actors = new ArrayList<>();
        removeQueue = new ArrayList<>();
        addTeam1Queue = new ArrayList<>();
        addTeam2Queue = new ArrayList<>();
    }

    public void add(actor actor, ArrayList<actor> team) {
        if (team == team1Actors) {
            addTeam1Queue.add(actor);
        } else if (team == team2Actors) {
            addTeam2Queue.add(actor);
        }
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

        // modify all the arrays that need to be modified
        for (actor actor : removeQueue) {
            team1Actors.remove(actor);
            team2Actors.remove(actor);
        }

        if (!addTeam1Queue.isEmpty()) team1Actors.addAll(addTeam1Queue);
        if (!addTeam2Queue.isEmpty()) team2Actors.addAll(addTeam2Queue);

        removeQueue.clear();
        addTeam1Queue.clear();
        addTeam2Queue.clear();
    }
}
