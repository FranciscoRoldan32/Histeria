package view;

import javax.swing.*;

import model.entity.Score;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ScoreScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea scoreArea;
    private JButton btnBack;

    public ScoreScreen() {
        setLayout(new BorderLayout());

        scoreArea = new JTextArea();
        scoreArea.setEditable(false);

        btnBack = new JButton("Volver al Menú");

        add(new JScrollPane(scoreArea), BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }

    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }

    public void updateScores(List<Score> scores) {
        scoreArea.setText("");
        for (Score score : scores) {
            scoreArea.append(score.getPlayerName() + ": " + score.getPoints() + " puntos\n");
        }
    }
}
