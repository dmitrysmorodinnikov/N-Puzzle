package search.heuristics;

import search.Node;

/**
 * 
 * Out of Row and Out of Column heuristic
 *
 */
public class OutOfRow implements Heuristic{
	
	public int getDistance(Node node1, Node node2){
		int rowsTiles = 0;
		int columnTiles = 0;
		int[] x1 = new int[9];
		int[] y1 = new int[9];
		int[] x2 = new int[9];
		int[] y2 = new int[9];
		for(int i=0;i<9;i++){
			x1[i] = -1;
			y1[i] = -1;
			x2[i] = -1;
			y2[i] = -1;
		}
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				 int val1 = node1.arr[i][j];
				 int val2 = node2.arr[i][j];
				 x1[val1] = j;
				 y1[val1] = i;
				 x2[val2] = j;
				 y2[val2] = i;
			}
		}
		
		for(int i=0;i<9;i++){
			if(x1[i]!=x2[i])
				rowsTiles++;
			if(y1[i]!=y2[i])
				columnTiles++;
		}
		
		return rowsTiles + columnTiles;
	}
}
