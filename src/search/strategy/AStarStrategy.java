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
 * A* search implementation
 *
 */
public class AStarStrategy extends Strategy {
	
	private Queue<Node> queue;	
	
	public AStarStrategy(Problem problem, Heuristic heuristic) {
		super(problem,heuristic);
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
				setCost(child,problem.getCurrentNode());				
			}			
			queue.addAll(children);
			if(queue.size() == 0){
				problem.setSolvable(false);
				break;
			}
		}		
	}

	private void setCost(Node child,Node parent){		
		int parentChild = heuristic.getDistance(child, parent);
		int future = heuristic.getDistance(child, problem.getGoalNode());
		int past = Math.min(parent.getCost() + parentChild, child.getCost());
		int cost = Math.min(child.getCost(), future+past);
		child.setCost(cost);
	}
}
