package controller;

import view.MainMenu;
import view.ScoreScreen;
import view.GameScreen;

import javax.swing.*;

import model.service.MatrixService;
import model.service.ScoreService;

import java.awt.*;

public class GameController {
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private ScoreScreen scoreScreen;
	private MatrixService matrixService;

	public GameController() {
		frame = new JFrame("Juego en Java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		mainMenu = new MainMenu();
		gameScreen = new GameScreen(5, 5);
		scoreScreen = new ScoreScreen();
		matrixService = new MatrixService(5, 5);

		mainPanel.add(mainMenu, "Menu");
		mainPanel.add(gameScreen, "Juego");
		mainPanel.add(scoreScreen, "Puntajes");

		frame.add(mainPanel);
		frame.setVisible(true);

		setupListeners();
	}

	private void setupListeners() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		mainMenu.addPlayListener(e -> {
			matrixService.Init();

			gameScreen.setButtonMatrix(matrixService.getMatrix());
			cardLayout.show(mainPanel, "Juego");

			JButton[][] buttons = gameScreen.getMatrix();

			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[i].length; j++) {

					int buttonId = Integer.parseInt(buttons[i][j].getActionCommand());

					buttons[i][j].addActionListener(event -> handleButtonClick(buttonId));
				}
			}

		});

		mainMenu.addScoresListener(e -> {
			scoreScreen.updateScores(matrixService.getListScore());
			cardLayout.show(mainPanel, "Puntajes");
		});

		mainMenu.addExitListener(e -> System.exit(0));

		gameScreen.addBackListener(e -> {
			matrixService.resetScore();
			gameScreen.updateScore(matrixService.getScore());
			cardLayout.show(mainPanel, "Menu");
		});

		scoreScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
	}

	private void handleButtonClick(int buttonId) {
		try {
			gameScreen.updateMatrix(matrixService.getButtonAndAdjancents(buttonId));
			gameScreen.updateScore(matrixService.getScore());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
