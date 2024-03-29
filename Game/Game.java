package Game;

import java.util.ArrayList;

public class Game {
    public ArrayList<Actor> team1Actors;
    public ArrayList<Actor> team2Actors;
    ArrayList<Actor> addTeam1Queue;
    ArrayList<Actor> addTeam2Queue;
    ArrayList<Actor> removeQueue;

    private final int x1Boundary = 0;
    private final int x2Boundary = 3200;
    private final int y1Boundary = 0;
    private final int y2Boundary = 3200;

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

    // 0 for still playing, 1 for team 1 wins, 2 for team 2 wins, -1 for tie
    public int checkWinner() {
        Base team1Base = (Base) team1Actors.get(0);
        Base team2Base = (Base) team2Actors.get(0);

        if (team1Base.getHp() <= 0 && team2Base.getHp() <= 0) {
            return -1;
        } else if (team1Base.getHp() <= 0) {
            return 2;
        } else if (team2Base.getHp() <= 0) {
            return 1;
        }

        return 0;
    }

    public int getX1Boundary() {
        return x1Boundary;
    }

    public int getX2Boundary() {
        return x2Boundary;
    }

    public int getY1Boundary() {
        return y1Boundary;
    }

    public int getY2Boundary() {
        return y2Boundary;
    }
}
