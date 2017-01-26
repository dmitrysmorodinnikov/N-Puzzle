package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * represents the state in the 8-puzzle
 *
 */
public class Node implements Comparable<Node> {
	public int arr[][] = new int[3][3];
	private int cost = Integer.MAX_VALUE;
	private int level = 0;
	private Node parent = null;
	
	public Node(int[][] arr){		
		this.arr = arr;	
	}
	
	public Node(Node anotherNode){		
		for(int i=0;i<anotherNode.arr.length;i++){
			this.arr[i] = Arrays.copyOf(anotherNode.arr[i], anotherNode.arr.length);
		}		
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * get the coordinates of the blank tile
	 * @return
	 */
	public Coord getBlankLocation(){		
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){
				if(arr[i][j] == 0){
					return new Coord(i,j);
				}
			}
		}
		return null;
	}
	
	/**
	 * get possible tiles to move to
	 * @return
	 */
	public List<Coord>getAdjacentLocations(){
		List<Coord>list = new ArrayList<>();
		Coord blankLocation = getBlankLocation();
		if(blankLocation.getX() == 1 && blankLocation.getY() == 2){
			int n =9;
			int t = n;
		}			
		Coord top = new Coord(blankLocation.getX(), blankLocation.getY()+1);
		Coord down = new Coord(blankLocation.getX(), blankLocation.getY()-1);
		Coord left = new Coord(blankLocation.getX()-1, blankLocation.getY());
		Coord right = new Coord(blankLocation.getX()+1, blankLocation.getY());
		
		if(top.locationIsValid(arr.length))
			list.add(top);
		if(down.locationIsValid(arr.length))
			list.add(down);
		if(right.locationIsValid(arr.length))
			list.add(right);
		if(left.locationIsValid(arr.length))
			list.add(left);
		
		return list;
	}

	@Override
	public int compareTo(Node n) {
		if(this.cost > n.getCost())
			return 1;		
		if(this.cost < n.getCost())
			return -1;
		return 0;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;		
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(){
		if(parent == null)
			this.level = 0;
		else
			this.level = parent.getLevel()+1;
	}
}
