package search;

/**
 * 
 * Wrapper for x,y coordinates
 *
 */
public class Coord {
	private int x;
	private int y;
	
	public Coord(int x, int y){
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * check if location is within n-puzzle boundaries
	 * @param n
	 * @return
	 */
	public boolean locationIsValid(int n){
		return getX()<n && getX()>=0 && getY()<n && getY()>=0;
	}
}
