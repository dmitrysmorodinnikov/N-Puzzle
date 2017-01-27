package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import search.Coord;
import search.Node;

/**
 * 
 * Represents a problem that needs to be solved - including root state and goal state
 *
 */
public class Problem {
	private Node rootNode;
	private Node goalNode;
	private Node currentNode;
	private Map<Integer, Boolean>track;
	private boolean isSolvable = true;
	
	public Problem(Node rootNode, Node goalNode){
		setRootNode(rootNode);
		rootNode.setCost(0);
		setGoalNode(goalNode);
		setCurrentNode(rootNode);
		track = new HashMap<>();
	}

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {		
		this.rootNode = rootNode;
	}

	public Node getGoalNode() {
		return goalNode;
	}

	public void setGoalNode(Node goalNode) {
		this.goalNode = goalNode;
	}
	
	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;		
	}
	
	/**
	 * Check if the problem is solved
	 * @param node
	 * @return
	 */
	public boolean isSolved(Node node){
		boolean equal = true;
		int [][] currentArr = node.arr;
		int [][] goalArr = goalNode.arr;
		for(int i =0;i<currentArr.length;i++){
			if(!equal)
				break;
			for(int j=0;j<currentArr.length;j++){
				if(currentArr[i][j] != goalArr[i][j]){
					equal = false;
					break;
				}
			}
		}
		return equal;
	}

	/**
	 * logic for expansion of the node
	 * @return
	 */
	public List<Node>expandNode(){
		currentNode.setLevel();
		List<Node>children = new ArrayList<Node>();
		Coord blankLocation = currentNode.getBlankLocation();
		List<Coord> adjacentLocations = currentNode.getAdjacentLocations();
		for(Coord adjLocation:adjacentLocations){
			Node child = new Node(currentNode);
			child.arr[blankLocation.getX()][blankLocation.getY()]
					= currentNode.arr[adjLocation.getX()][adjLocation.getY()];
			child.arr[adjLocation.getX()][adjLocation.getY()]
					= currentNode.arr[blankLocation.getX()][blankLocation.getY()];
			if(!ifDuplicate(child)){
				child.setParent(currentNode);
				children.add(child);
				addToTrack(child);				
			}			
		}		
		return children;
	}	
	
	/**
	 * duplicate states are not added to the tree
	 * @param node
	 * @return
	 */
	private boolean ifDuplicate(Node node){
		StringBuilder sb = new StringBuilder();
		int[][]arr = node.arr;
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){
				sb.append(arr[i][j]);
			}
		}
		String s = sb.toString();
		int val = Integer.parseInt(s);
		return track.containsKey(val);
	}
	
	private void addToTrack(Node node){
		StringBuilder sb = new StringBuilder();
		int[][]arr = node.arr;
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){
				sb.append(arr[i][j]);
			}
		}
		String s = sb.toString();
		int val = Integer.parseInt(s);
		track.put(val, true);
	}

	/**
	 * 8-puzzle can be unsolvable
	 * @return
	 */
	public boolean isSolvable() {
		return isSolvable;
	}

	public void setSolvable(boolean isSolvable) {
		this.isSolvable = isSolvable;
	}	
}
