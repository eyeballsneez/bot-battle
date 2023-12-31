package Game;

import java.util.ArrayList;

public class Game {
    public ArrayList<Actor> team1Actors;
    public ArrayList<Actor> team2Actors;
    ArrayList<Actor> addTeam1Queue;
    ArrayList<Actor> addTeam2Queue;
    ArrayList<Actor> removeQueue;

    public Game() {
        team1Actors = new ArrayList<>();
        team2Actors = new ArrayList<>();
        removeQueue = new ArrayList<>();
        addTeam1Queue = new ArrayList<>();
        addTeam2Queue = new ArrayList<>();
    }

    public void add(Actor actor, ArrayList<Actor> team) {
        if (team == team1Actors) {
            addTeam1Queue.add(actor);
        } else if (team == team2Actors) {
            addTeam2Queue.add(actor);
        }
    }
    public void remove(Actor actor) {
        removeQueue.add(actor);
    }

    public void tick() {
        // not using enhanced for-loop to prevent concurrent modification error
        for (Actor team1Actor : team1Actors) {
            team1Actor.tick();
        }

        for (Actor team2Actor : team2Actors) {
            team2Actor.tick();
        }

        // modify all the arrays that need to be modified
        for (Actor actor : removeQueue) {
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
