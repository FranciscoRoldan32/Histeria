package model.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import model.entity.*;

public class MatrixService {
	private int row = 5;
	private int column = 5;
	private int[][] matrix;
	private int id;
	private Set<Integer> listAdjacentsAux;
	private Map<Integer, Button> buttons;
	private Color colorDefault = new Color(200, 200, 200);
	private ScoreService _scoreService = new ScoreService();

	private Color[] listColorsDefault = new Color[] { new Color(197, 108, 240), new Color(50, 255, 126),
			new Color(255, 56, 56), new Color(255, 159, 26), new Color(255, 242, 0), new Color(24, 220, 255) };

	/**
	 * @param row
	 * @param column
	 */
	public MatrixService(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		matrix = new int[row][column];
		buttons = new HashMap<>();
		listAdjacentsAux = new HashSet<>();
	}

	public void Init() {

		try {
			createMatrixAndDictionary();
			AssignAdjacents();

			resetData();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public int getScore() {
		return _scoreService.getScore();
	}
	
	public List<Score> getListScore(){
		return _scoreService.getScores();
	}
	
	public void incrementScore() {
		_scoreService.addPoint();
	}
	
	public void resetScore() {
		_scoreService.resetScore();
	}
	

	public List<Button> getButtonAndAdjancents(int id) throws Exception {

		if (id < 0 || id >= this.buttons.size())
			throw new Exception("El id no es válido.");

		List<Button> list = new ArrayList<>();

		compareButtonWithAdjacent(buttons.get(id));
		list.add(buttons.get(id));

		for (Integer adj : buttons.get(id).getAdjacents()) {
			list.add(buttons.get(adj));
		}
		incrementScore();
		return list;
	}

	public Button[][] getMatrix() {

		Button[][] matrixButtons = new Button[this.row][this.column];
		Button currentButton = null;

		for (int i = 0; i < buttons.size(); i++) {
			currentButton = buttons.get(i);
			matrixButtons[currentButton.getPair().getX()][currentButton.getPair().getY()] = currentButton;
		}
		return matrixButtons;
	}

	private void createMatrixAndDictionary() {
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				matrix[j][i] = id;

				Pair par = new Pair(j, i);
				Button button = new Button(id, colorDefault, par);
				buttons.put(id, button);
				this.id++;
			}
		}
	}

	private void compareButtonWithAdjacent(Button button) {

		button.setColor(colorRandom());

		for (Integer adj : button.getAdjacents()) {

			Button AdjButton = buttons.get(adj);

			if (button.getColor().equals(AdjButton.getColor())) {
				button.setColor(colorDefault);
				disabledAdjacents(button);
			}
		}
	}

	private void disabledAdjacents(Button button) {
		for (Integer adj : button.getAdjacents()) {
			Button adjButton = buttons.get(adj);
			adjButton.setColor(colorDefault);
		}
	}

	private void AssignAdjacents() {

		Pair par;
		int x, y;

		for (int i = 0; i < buttons.size(); i++) {

			par = buttons.get(i).getPair();
			x = par.getX();
			y = par.getY();

			if (x > 0 && y > 0 && x < (this.row - 1) && y < (this.column - 1)) {
				assignsInternalPairs(par);
				listAdjacentsAux.remove(i);
				buttons.get(i).setAdjacents(listAdjacentsAux);
				listAdjacentsAux.clear();
			} else {
				assignsExternalPairs(par);
				listAdjacentsAux.remove(i);
				buttons.get(i).setAdjacents(listAdjacentsAux);
				listAdjacentsAux.clear();
			}
		}
	}

	private void assignsInternalPairs(Pair par) {

		int x = par.getX() - 1;
		int y = par.getY() - 1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				listAdjacentsAux.add(matrix[x][y]);
				y++;
			}
			y = par.getY() - 1; // reinicio X
			x++;
		}
	}

	private void assignsExternalPairs(Pair par) {

		int x = par.getX();
		int y = par.getY();

		// recorre primer fila => y == 0
		if (y == 0) {
			for (int i = 0; i < this.row; i++) {

				if (i == 0 || i == (this.row - 1)) {
					assignsAtExtremes(par);
				} else {
					// (0;1)(0;2) buscar los hijos de estos puntos
					assignsAtRowAndColumnBorder(par);
				}
			}
		}

		// Recorre primer columna => x == 0
		if (x == 0) {

			for (int i = 0; i < this.column; i++) {

				if (i == 0 || i == (this.column - 1)) {
					// (o;o) extremo ARRIBA IZQ / (0;4)extremo ABAJO IZQ
					assignsAtExtremes(par);
				} else {
					// (0;1)(0;2) buscar los hijos de estos puntos
					assignsAtRowAndColumnBorder(par);
				}
			}
		}

		// recorre ultima columna => x == row.length
		if (x == this.row - 1) {

			for (int i = 0; i < this.column; i++) {

				if (i == 0 || i == (this.column - 1)) {
					// (4;0) extremo ARRIBA DER (4;4) extremo ABAJO DER
					assignsAtExtremes(par);
				} else {
					// (4;1)(4;2) buscar los hijos de estos puntos
					assignsAtRowAndColumnBorder(par);
				}
			}
		}

		// recorre ultima fila => y == column.length
		if (y == this.column - 1) {
			for (int i = 0; i < this.row; i++) {

				if (i == 0 || i == (this.row - 1)) {
					// (0;4) extremo ABAJO IZQ (4;4) extremo ABAJO DER
					assignsAtExtremes(par);
				} else {
					// (1;4)(2;4) buscar los hijos de estos puntos
					assignsAtRowAndColumnBorder(par);
				}
			}
		}

	}

	private void assignsAtExtremes(Pair par) {
		int x = par.getX();
		int y = par.getY();

		if (x == 0 && y == 0) {
			assignsAdjacentsToExtremes(x, y);
		}

		if (x == this.row - 1 && y == 0) {
			assignsAdjacentsToExtremes(this.row - 2, y);
		}

		if (x == 0 && y == this.column - 1) {
			assignsAdjacentsToExtremes(x, this.column - 2);
		}

		if (x == this.row - 1 && y == this.column - 1) {
			assignsAdjacentsToExtremes(this.row - 2, this.column - 2);
		}
	}

	private void assignsAtRowAndColumnBorder(Pair par) {
		int x = par.getX();
		int y = par.getY();

		if (x > 0 && x < this.row - 1 && y < 1) {
			assignsAdjacentsToRows(x - 1, y);
			return;
		}

		if (x > 0 && x < this.row - 1 && y == this.column - 1) {
			assignsAdjacentsToRows(x - 1, this.column - 2);
			return;
		}

		if (y > 0 && y < this.column - 1 && x < 1) {
			assignsAdjacentsToColumns(x, y - 1);
			return;
		}

		if (y > 0 && y < this.column - 1 && x == this.row - 1) {
			assignsAdjacentsToColumns(this.row - 2, y - 1);
			return;
		}
	}

	private void assignsAdjacentsToExtremes(int a, int b) {
		for (int i = a; i < a + 2; i++) {
			for (int j = b; j < b + 2; j++) {
				listAdjacentsAux.add(matrix[i][j]);
			}
		}
	}

	// recorre 6 posiciones, 2 columnas y 3 filas
	private void assignsAdjacentsToColumns(int a, int b) {
		for (int i = a; i < a + 2; i++) {
			for (int j = b; j < b + 3; j++) {
				listAdjacentsAux.add(matrix[i][j]);
			}
		}
	}

	// recorre 6 posiciones, 3 columnas y 2 filas
	private void assignsAdjacentsToRows(int a, int b) {
		for (int i = a; i < a + 3; i++) {
			for (int j = b; j < b + 2; j++) {
				listAdjacentsAux.add(matrix[i][j]);
			}
		}
	}

	private Color colorRandom() {

		int min = 0, max = listColorsDefault.length - 1;
		int randomNumber;
		Random random = new Random();
		randomNumber = random.nextInt(max - min + 1) + min;
		return listColorsDefault[randomNumber];
	}

	private void resetData() {
		this.id = 0;
	}

}
