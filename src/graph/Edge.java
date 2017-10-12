package graph;

import java.util.Set;
import java.util.function.Function;

// Understand the connection between the neighbours
public class Edge {

    static final Function<Edge, Double> HOP_STRATEGY = edge -> 1.0;
    static final Function<Edge, Double> COST_STRATEGY = edge -> edge.cost;

    private final Node neighbor;
    private final double cost;

    public Edge(Node neighbor, double cost) {
        this.neighbor = neighbor;
        this.cost = cost;
    }

    double visit(Node destination, Set<Node> visitedNodes, Function<Edge, Double> strategy) {
        return neighbor.findDestination(destination, visitedNodes, strategy) + strategy.apply(this);
    }
}
