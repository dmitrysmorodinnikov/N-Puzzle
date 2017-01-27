## Solving N-Puzzle problem by using Symbolic Search
The goal of this practical is to design, implement and analyze the behavior of various AI
tree-based Search strategies, both informed and uninformed, for solving n-puzzle problem. The
essence of the problem is to find the cheapest cost path between two connectable n-puzzle
configurations. 

## Search strategies
Two uninformed and two informed algorithms are implemented:
* Breadth-First Search
* Uniform-Cost Search
* Greedy Best-First Search
* A Star

Three heuristic functions are implemented to estimate the path cost from the
node n to the goal node: 
* Manhattan Distance
* Misplaced Tiles
* 'Out of row and out of column' heuristic

## Documentation
The detailed report containing the description of the problem, search algorithms, heuristic functions and comparison of different
search algorithms' performances can be found in '/doc/Report.pdf'.

## Author
* [@d_smorodinnikov](https://twitter.com/d_smorodinnikov) <br/>
* [Dmitry Smorodinnikov's Blog](http://smorodinnikov.com/)<br/>
