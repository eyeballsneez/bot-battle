import java.util.ArrayList;

public class Game {
    ArrayList<actor> team1Actors;
    ArrayList<actor> team2Actors;

    public Game() {
        team1Actors = new ArrayList<>();
        team2Actors = new ArrayList<>();
    }

    public void tick() {
        // not using enhanced for-loop to prevent concurrent modification error
        for (int i = 0; i < team1Actors.size(); i++) {
            team1Actors.get(i).tick();
        }

        for (int i = 0; i < team2Actors.size(); i++) {
            team2Actors.get(i).tick();
        }
    }
}
