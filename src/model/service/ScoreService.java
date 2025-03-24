package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entity.Score;

public class ScoreService {
    private List<Score> scoreList = new ArrayList<>();

    public void addScore(String playerName, int points) {
        scoreList.add(new Score(playerName, points));
    }

    public List<Score> getScores() {
        return scoreList;
    }
}
