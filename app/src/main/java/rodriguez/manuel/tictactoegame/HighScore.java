package rodriguez.manuel.tictactoegame;

/**
 * Represents a high score achieved by a player.
 */
public class HighScore {

    private String playerName;
    private int score;

    /**
     * Constructs a new HighScore with the specified player name and score.
     *
     * @param playerName The name of the player.
     * @param score      The score achieved by the player.
     */
    public HighScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Returns the name of the player.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Returns the score achieved by the player.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a string representation of the high score.
     *
     * @return A string containing the player's name and score.
     */
    @Override
    public String toString() {
        return playerName + ", " + score;
    }
}
