package controller;

import view.MainMenu;
import view.ScoreScreen;
import view.GameScreen;

import javax.swing.*;

import model.service.MatrixService;
import model.service.ScoreService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private ScoreScreen scoreScreen;
	private ScoreService scoreService;
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
		scoreService = new ScoreService();
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
				for (int j = 0; j < buttons.length; j++) {

					int rowAux = i;
					int columnAux = j;

					buttons[i][j].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							int buttonId = Integer.parseInt(buttons[rowAux][columnAux].getActionCommand());

							try {
								matrixService.getButtonAndAdjancents(buttonId);

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				}
			}
		});

		mainMenu.addScoresListener(e -> {
			scoreScreen.updateScores(scoreService.getScores());
			cardLayout.show(mainPanel, "Puntajes");
		});

		mainMenu.addExitListener(e -> System.exit(0));

		gameScreen.addBackListener(e -> {
			scoreService.addScore("Jugador", (int) (Math.random() * 100)); // Simula un puntaje
			cardLayout.show(mainPanel, "Menu");
		});

		scoreScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
	}
}
