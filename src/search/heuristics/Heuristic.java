package search.heuristics;

import search.Node;

/**
 * 
 * common interface for evaluation functions
 *
 */
public interface Heuristic {

	int getDistance(Node node1, Node node2);
}
