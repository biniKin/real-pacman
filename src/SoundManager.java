import javafx.scene.media.AudioClip;

/**
 * Manages game sound effects
 */
public class SoundManager {
    
    private static boolean soundEnabled = true;
    
    // Sound effect types
    public enum Sound {
        EAT_DOT,
        EAT_POWER_PELLET,
        EAT_GHOST,
        DEATH,
        POWER_UP,
        LEVEL_COMPLETE
    }
    
    /**
     * Plays a sound effect
     */
    public static void playSound(Sound sound) {
        if (!soundEnabled) return;
        
        try {
            // Generate simple beep sounds using JavaFX AudioClip
            // In a full implementation, you would load actual sound files
            switch (sound) {
                case EAT_DOT:
                    playBeep(800, 50);
                    break;
                case EAT_POWER_PELLET:
                    playBeep(1200, 200);
                    break;
                case EAT_GHOST:
                    playBeep(1500, 300);
                    break;
                case DEATH:
                    playDeathSound();
                    break;
                case POWER_UP:
                    playPowerUpSound();
                    break;
                case LEVEL_COMPLETE:
                    playLevelCompleteSound();
                    break;
            }
        } catch (Exception e) {
            // Silently fail if sound doesn't work
            System.out.println("Sound error: " + e.getMessage());
        }
    }
    
    /**
     * Plays a simple beep (placeholder for actual sound files)
     */
    private static void playBeep(int frequency, int duration) {
        // This is a placeholder - in a real implementation you would load .wav or .mp3 files
        // For now, we'll just print to console
        System.out.println("♪ Sound: " + frequency + "Hz for " + duration + "ms");
    }
    
    /**
     * Plays death sound sequence
     */
    private static void playDeathSound() {
        System.out.println("♪ Death sound");
    }
    
    /**
     * Plays power-up collection sound
     */
    private static void playPowerUpSound() {
        System.out.println("♪ Power-up collected!");
    }
    
    /**
     * Plays level complete sound
     */
    private static void playLevelCompleteSound() {
        System.out.println("♪ Level Complete!");
    }
    
    /**
     * Enables or disables sound
     */
    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
    }
    
    /**
     * Checks if sound is enabled
     */
    public static boolean isSoundEnabled() {
        return soundEnabled;
    }
}
