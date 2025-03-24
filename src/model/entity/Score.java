package model.entity;

public class Score {
    private String playerName;
    private int points;

    public Score(String playerName, int points) {
        this.playerName = playerName;
        this.points = points;
    }

    public String getPlayerName() { return playerName; }
    public int getPoints() { return points; }
}
