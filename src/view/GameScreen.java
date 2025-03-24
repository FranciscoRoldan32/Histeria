package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.entity.Button;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack;
    private JLabel lblGame;
    private JPanel panelGrid;
    private JButton btnAux;
    private JButton[][] btnsMatrix;
    
    public GameScreen(int row, int column) {
    	
    	btnsMatrix = new JButton[row][column];
        setLayout(new BorderLayout());

        lblGame = new JLabel("¡Felicitaciones! Haz ganado con X movimientos.", SwingConstants.CENTER);
        lblGame.setFont(new Font("Arial", Font.BOLD, 20));

        btnBack = new JButton("Volver al Menú");

        add(lblGame, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
        
        panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(row, column, 0, 0));
        
        add(panelGrid, BorderLayout.CENTER);
    }

    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void setButtonMatrix(Button[][] buttons) {
        panelGrid.removeAll();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                model.entity.Button currentButton = buttons[j][i]; 
                btnAux = new JButton();

                int buttonId = currentButton.getId();
                btnAux.setActionCommand(Integer.toString(buttonId));
                btnAux.setBackground(currentButton.getColor());
                btnAux.setText("<html>Id: " + buttonId + "<br>" + currentButton.getPair().toString() + "</html>");

                btnsMatrix[j][i] = btnAux;
                panelGrid.add(btnAux);
            }
        }

        panelGrid.revalidate();
        panelGrid.repaint();
    } 
    
    public JButton[][] getMatrix(){
    	return btnsMatrix;
    }
    
    public void updateMatrix(java.util.List<Button> list) {

    	for (Button button : list) {
            int buttonId = button.getId();
            int x = button.getPair().getX();
            int y = button.getPair().getY();
            
            btnsMatrix[x][y].setActionCommand(Integer.toString(buttonId));
            btnsMatrix[x][y].setBackground(button.getColor());
            btnsMatrix[x][y].setText("<html>Id: " + buttonId + "<br>" + button.getPair().toString() + "</html>");
            
    		btnsMatrix[x][y].setBackground(button.getColor());
    		
		}
        panelGrid.revalidate();
        panelGrid.repaint();
    }
}










