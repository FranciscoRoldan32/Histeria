package model.entity;

public class Score {
	private String playerName;
	private int points = 0;

	public Score(String playerName, int points) {
		this.playerName = playerName;
		this.points = points;
	}

	public void setPoints(int point) {
		this.points = point;
	}

	public void setPoints() {
		this.points++;
	};

	public int getPoints() {
		return this.points;
	}

	public String getPlayerName() {
		return this.playerName;
	}
}
