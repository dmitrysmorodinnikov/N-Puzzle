package search.strategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import problem.Problem;
import search.Node;
import search.heuristics.Heuristic;

/**
 * 
 * Breadth-first search implementation
 *
 */
public class BfsStrategy extends Strategy {

	private Queue<Node> queue;
	
	public BfsStrategy(Problem problem, Heuristic heuristic) {
		super(problem,heuristic);
		queue = new LinkedList<>();		
	}

	@Override
	public void search() {		
		queue.add(problem.getRootNode());		
		while(!queue.isEmpty()){
			Node node = queue.poll();
			problem.setCurrentNode(node);			
			List<Node>children = problem.expandNode();
			nodesExpanded++;
			if(nodesExpandedPerLevel.containsKey(problem.getCurrentNode().getLevel())){
				nodesExpandedPerLevel.put(problem.getCurrentNode().getLevel(),
						nodesExpandedPerLevel.get(problem.getCurrentNode().getLevel())+1);
			}
			else{
				nodesExpandedPerLevel.put(problem.getCurrentNode().getLevel(),1);					
			}
			if(children.stream().anyMatch(x->problem.isSolved(x))){				
				problem.getGoalNode().setParent(node);
				break;
			}				
			queue.addAll(children);
			if(queue.size() == 0){
				problem.setSolvable(false);
				break;
			}
		}		
	}
	
	
}
