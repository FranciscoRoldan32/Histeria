package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnPlay, btnScores, btnExit;

    public MainMenu() {
        setLayout(new GridLayout(3, 1));

        btnPlay = new JButton("Jugar");
        btnScores = new JButton("Historial de Puntajes");
        btnExit = new JButton("Salir");

        add(btnPlay);
        add(btnScores);
        add(btnExit);
    }

    public void addPlayListener(ActionListener listener) {
        btnPlay.addActionListener(listener);
    }

    public void addScoresListener(ActionListener listener) {
        btnScores.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }
}
