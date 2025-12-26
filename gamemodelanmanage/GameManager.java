
import java.util.function.Consumer;

public class GameManager {
    private GameMode mode;
    private final Scoreboard scoreboard = new Scoreboard();
    private final RoundManager roundManager = new RoundManager();
    private Consumer<String> lanSyncCallback;
    private Runnable powerPelletEffect;

    public void selectGameMode(GameMode mode) { this.mode = mode; System.out.println("Game mode: " + mode); }
    public void addPlayer(String name) { scoreboard.addPlayer(name); System.out.println("Player added: " + name); }
    public void addScore(String player, int points) { scoreboard.addScore(player, points); }
    public void displayScoreboard() { scoreboard.display(); }
    public void resetScores() { scoreboard.reset(); }
    public void startRound() {
        try { roundManager.startRound(); if (lanSyncCallback != null) lanSyncCallback.accept("Round started"); }
        catch(Exception e){ System.out.println(e.getMessage()); }
    }
    public void endRound() {
        try { roundManager.endRound(); if (lanSyncCallback != null) lanSyncCallback.accept("Round ended"); }
        catch(Exception e){ System.out.println(e.getMessage()); }
    }
    public void setLanSyncCallback(Consumer<String> callback) { lanSyncCallback = callback; }
    public void setPowerPelletEffect(Runnable effect) { powerPelletEffect = effect; }
    public void triggerPowerPellet() { if(powerPelletEffect != null) powerPelletEffect.run(); }
}
