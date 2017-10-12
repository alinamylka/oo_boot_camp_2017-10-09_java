package graph;

import java.util.Set;

// Understand the connection between the neighbours
public class Edge {

    static final CostStrategy HOP_STRATEGY = edge -> 1.0;
    static final CostStrategy COST_STRATEGY = edge -> edge.cost;

    private final Node neighbor;
    private final double cost;

    public Edge(Node neighbor, double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("cost can not be negative");
        }
        this.neighbor = neighbor;
        this.cost = cost;
    }

    double findDestination(Node destination, Set<Node> visitedNodes, CostStrategy costStrategy) {
        return neighbor.findDestination(destination, visitedNodes, costStrategy) + costStrategy.cost(this);
    }

    @FunctionalInterface
    interface CostStrategy {
        double cost(Edge edge);
    }
}
