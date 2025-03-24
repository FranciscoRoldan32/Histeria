package model.entity;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Button {

	private int id;
	private Color color;
	private Pair par;
	private Set<Integer> adjacents = new HashSet<>();

	/**
	 * @param id
	 * @param color
	 * @param adjacents
	 * @param par
	 */
	public Button(int id, Color color, Pair par) {
		super();
		this.id = id;
		this.color = color;
		this.par = par;
	}

	public Pair getPair() {
		return par;
	}

	public int getId() {
		return id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setAdjacents(Set<Integer> list) {
		this.adjacents.addAll(list);
	}

	public Set<Integer> getAdjacents() {
		return adjacents;
	}

	@Override
	public String toString() {
		return "Button [id=" + id + ", color=" + color + " par=" + par + ", adjacents=" + adjacents + "]";
	}
}
