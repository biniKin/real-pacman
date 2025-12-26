
import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private final Map<String, Integer> scores = new HashMap<>();

    public void addPlayer(String name) {
        if (!scores.containsKey(name)) scores.put(name, 0);
    }

    public void addScore(String name, int points) {
        if (!scores.containsKey(name)) {
            System.out.println("Player " + name + " not found!");
            return;
        }
        scores.put(name, scores.get(name) + points);
    }

    public void reset() {
        scores.replaceAll((k, v) -> 0);
    }

    public void display() {
        System.out.println("----- Scoreboard -----");
        scores.forEach((player, score) -> System.out.println(player + ": " + score));
        System.out.println("----------------------");
    }
}
