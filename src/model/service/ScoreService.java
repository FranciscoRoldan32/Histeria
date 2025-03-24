package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entity.Score;

public class ScoreService {
	
    private List<Score> scoreList = new ArrayList<>();
    private Score currentUser = new Score("Jugador", 0); 
    
    public void addScoreAndUser(String playerName, int points) {
        scoreList.add(new Score(playerName, points));
    }
    
    public void addPoint() {
    	currentUser.setPoints();
    }
    
    public int getScore() {
    	return currentUser.getPoints();
    }

    public List<Score> getScores() {
        return scoreList;
    }
    
    public void resetScore() {
    	currentUser.setPoints(0);
    }
}
