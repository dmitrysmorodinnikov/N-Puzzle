package search.strategy;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import problem.Problem;
import search.Node;
import search.heuristics.Heuristic;
import search.heuristics.Manhattan;

/**
 * 
 * Greedy best-first search implementation
 *
 */
public class GreedyStrategy extends Strategy {

	private Queue<Node> queue;	
	
	public GreedyStrategy(Problem problem, Heuristic heuristic) {
		super(problem, heuristic);
		queue = new PriorityQueue<>();		
	}

	@Override
	public void search() {
		queue.add(problem.getRootNode());		
		while(!queue.isEmpty()){
			problem.setCurrentNode(queue.poll());			
			if(problem.isSolved(problem.getCurrentNode())){
				problem.getGoalNode().setParent(problem.getCurrentNode().getParent());
				break;
			}				
			List<Node>children = problem.expandNode();		
			nodesExpanded++;
			if(nodesExpandedPerLevel.containsKey(problem.getCurrentNode().getLevel())){
				nodesExpandedPerLevel.put(problem.getCurrentNode().getLevel(),
						nodesExpandedPerLevel.get(problem.getCurrentNode().getLevel())+1);
			}
			else{
				nodesExpandedPerLevel.put(problem.getCurrentNode().getLevel(),1);					
			}
			for(Node child:children){
				setCost(child);					
			}			
			queue.addAll(children);
			if(queue.size() == 0){
				problem.setSolvable(false);
				break;
			}
		}		
	}
	
	private void setCost(Node child){		
		int dist = heuristic.getDistance(child, problem.getGoalNode());
		int cost = dist;
		cost = Math.min(child.getCost(), cost);
		child.setCost(cost);
	}
}
