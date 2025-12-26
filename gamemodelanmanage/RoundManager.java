
public class RoundManager {
    private int roundNumber = 0;
    private boolean active = false;

    public void startRound() throws GameStateException {
        if (active) throw new GameStateException("Round already active!");
        active = true;
        roundNumber++;
        System.out.println("Round " + roundNumber + " started.");
    }

    public void endRound() throws GameStateException {
        if (!active) throw new GameStateException("No active round to end!");
        active = false;
        System.out.println("Round " + roundNumber + " ended.");
    }

    public boolean isActive() { return active; }
    public int getRoundNumber() { return roundNumber; }
}
