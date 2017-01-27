import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import problem.Problem;
import search.Node;
import search.heuristics.*;
import search.strategy.*;

/**
 * 
 * Printing and interaction with the user
 *
 */
public class Main {

	public static void main(String[]args){
		Main pr = new Main();
		Node root, goal;
		Heuristic heuristic = null;
		Strategy str = null;
		int h = -1;
		int s = -1;
		Scanner sc = new Scanner(System.in);		
		
		System.out.println("Enter the start configuration as a single line;"
				+ " Important - 0 stands for an empty tile in 8-puzzle (for example, '123456780'):");
		String startStr = sc.nextLine();
		root = getNodeFromStr(startStr);
		System.out.println("Enter the goal configuration as a single line(for example, '123456780')"
				+ " Important - 0 stand for an empty tile in 8-puzzle (for example, '123456780'):");
		String goalStr = sc.nextLine();
		goal = getNodeFromStr(goalStr);
		
		System.out.println("Start configuration:"+getArrStr(root.arr));
		System.out.println("Goal configuration:"+getArrStr(goal.arr));
		
		Problem problem = new Problem(root, goal);
		
		System.out.println("Select the heuristic that will be used: type 1 for 'Manhattan' heuristic,"
				+ " type 2 for 'Misplaced Tiles' heuristic,"
				+ "type 3 for 'Out of Row and Column' heuristic: ");		
		
		h = sc.nextInt();
		if(h==-1){
			System.out.println("Invalid input");
			System.exit(0);
		}			
		else if(h == 1){
			heuristic = new Manhattan();
		}
		else if(h==2){
			heuristic = new MisplacedTiles();
		}
		else if(h==3){
			heuristic = new OutOfRow();
		}
				
		
		System.out.println("Select the search strategy that will be used:"
				+ "type 1 for 'BFS',"
				+ "type 2 for 'Uniform Cost',"
				+ "type 3 for 'Greedy Best-First',"
				+ "type 4 for 'A*':");
		s = sc.nextInt();
		if(s==-1){
			System.out.println("Invalid input");
			System.exit(0);
		}			
		else if(s == 1){
			str = new BfsStrategy(problem, heuristic);
		}
		else if(s==2){
			str = new UniformCostStrategy(problem, heuristic);
		}
		else if(s==3){
			str = new GreedyStrategy(problem, heuristic);
		}
		else if(s==4){
			str = new AStarStrategy(problem, heuristic);
		}		
		str.search();	
		
		System.out.println("////////////////////////////////////////////////////////");
		
		if(!problem.isSolvable()){
			System.out.println("Unsolvable configuration");
			return;
		}			
		
		System.out.println("Solution path:");
		printPath(str);
		System.out.println("////////////////////////////////////////////////////////");		
		printDepth(str);
		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("Number of Expanded Nodes:"+str.getNodesExpanded());	
		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("Number of Expanded Nodes per Level:");
		printExpandedPerLevel(str);
	}
	
	private static Node getNodeFromStr(String s){
		int[][] arr = new int[3][3];		
		int count = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				int val = Character.getNumericValue(s.charAt(count));
				arr[i][j] = val;
				count++;
			}
		}
		return new Node(arr);
	}
	
	private static void printPath(Strategy str){
		List<Node>path = str.getPath();
		for(int i=path.size()-1;i>=0;i--){
			System.out.println(getArrStr(path.get(i).arr));			
		}
	}
	
	private static String getArrStr(int[][]arr){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){
				sb.append(arr[i][j]);
			}
		}
		return sb.toString();
	}
	
	private static void printDepth(Strategy str){
		System.out.println("Depth:" + str.getDepth());
	}
	
	private static void printExpandedPerLevel(Strategy str){
		int level = 0;
		while(str.getNodesExpandedPerLevel().containsKey(level)){
			int val = level+1;
			System.out.println("Level "+val+":"+str.getNodesExpandedPerLevel().get(level));
			level++;
		}	
	}
}
