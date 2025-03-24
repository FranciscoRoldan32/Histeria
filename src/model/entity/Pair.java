package model.entity;

public class Pair {
    private int x;
    private int y;
    
	/**
	 * @param x
	 * @param y
	 */
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Par [x=" + x + ", y=" + y + "]";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
