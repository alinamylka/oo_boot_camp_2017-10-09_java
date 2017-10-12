package graph;

import java.util.Optional;
import java.util.Set;

// Understand the connection between the neighbours
public class Edge {

    private final Node neighbor;
    private final int cost;

    public Edge(Node neighbor, int cost) {

        this.neighbor = neighbor;
        this.cost = cost;
    }

    Optional<Integer> hopCount(Node destination, Set<Node> visitedNodes) {
        return neighbor.hopCount(destination, visitedNodes);
    }
}
