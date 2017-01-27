package search.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import problem.Problem;
import search.Node;
import search.heuristics.Heuristic;

/**
 * 
 * Parent class for all search strategies
 *
 */
public abstract class Strategy {	
	protected Problem problem;	
	protected Heuristic heuristic;
	protected List<Node>path;
	protected int nodesExpanded = 0;
	protected Map<Integer, Integer> nodesExpandedPerLevel;
	protected int depth = 0;
	
	public Strategy(Problem problem, Heuristic heuristic){		
		this.problem = problem;	
		this.heuristic = heuristic;
		this.path = new ArrayList<>();
		this.nodesExpandedPerLevel = new HashMap<>();
	}
	
	public abstract void search();	
	
	public List<Node>getPath(){	
		Node curr = problem.getGoalNode();
		while(curr!=null){
			path.add(curr);
			curr = curr.getParent();
			depth++;
		}
		return path;
	}
	
	public int getNodesExpanded(){
		return this.nodesExpanded;
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public Map<Integer,Integer> getNodesExpandedPerLevel(){
		return this.nodesExpandedPerLevel;
	}
}
