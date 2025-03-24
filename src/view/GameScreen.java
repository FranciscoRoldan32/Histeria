package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.entity.Button;

public class GameScreen extends JPanel {
    private JButton btnBack;
    private JLabel lblGame;
    private JPanel panelGrid;
    private JButton btnAux;
    private JButton[][] btnsMatrix;
    
    public GameScreen(int row, int column) {
    	
    	btnsMatrix = new JButton[row][column];
        setLayout(new BorderLayout());

        lblGame = new JLabel("¡Juego en progreso!", SwingConstants.CENTER);
        lblGame.setFont(new Font("Arial", Font.BOLD, 20));

        btnBack = new JButton("Volver al Menú");

        add(lblGame, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
        
        // Panel para la matriz de botones
        panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(row, column, 0, 0)); // Espaciado de 5px entre botones
        
        add(panelGrid, BorderLayout.CENTER);
    }

    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void setButtonMatrix(Button[][] buttons) {
        panelGrid.removeAll(); // Limpiar la vista antes de actualizar

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                model.entity.Button currentButton = buttons[j][i]; 
                btnAux = new JButton();

                int buttonId = currentButton.getId();
                btnAux.setActionCommand(Integer.toString(buttonId));
                btnAux.setBackground(currentButton.getColor());
//                btnAux.setForeground(Color.BLACK);
                btnAux.setEnabled(!currentButton.isActive());  // eliminar simbolo !
                btnAux.setText("<html>Id: " + buttonId + "<br>" + currentButton.getPair().toString() + "</html>");

                btnsMatrix[j][i] = btnAux;
                panelGrid.add(btnAux);
            }
        }

        panelGrid.revalidate(); // Refrescar la interfaz
        panelGrid.repaint();
    } 
    
    public JButton[][] getMatrix(){
    	return btnsMatrix;
    }
}










