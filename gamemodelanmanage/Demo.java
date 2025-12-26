

public class Demo {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.selectGameMode(GameMode.LAN_HOST);
        gm.addPlayer("Binyam");
        gm.addPlayer("Amir");
        gm.setLanSyncCallback(msg -> System.out.println("[LAN Sync] " + msg));
        gm.setPowerPelletEffect(() -> System.out.println("[Power Pellet] Ghosts frightened!"));
        gm.startRound();
        gm.addScore("BiniHC", 50);
        gm.addScore("Amir", 30);
        gm.displayScoreboard();
        gm.triggerPowerPellet();
        gm.endRound();
        gm.resetScores();
        gm.displayScoreboard();
    }
}
