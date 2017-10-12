package graph;

import java.util.Set;

// Understand the connection between the neighbours
public class Edge {

    static final CostFunction HOP_STRATEGY = edge -> 1.0;
    static final CostFunction COST_STRATEGY = edge -> edge.cost;

    private final Node neighbor;
    private final double cost;

    public Edge(Node neighbor, double cost) {
        this.neighbor = neighbor;
        this.cost = cost;
    }

    double findDestination(Node destination, Set<Node> visitedNodes, CostFunction strategy) {
        return neighbor.findDestination(destination, visitedNodes, strategy) + strategy.computeCost(this);
    }

    @FunctionalInterface
    interface CostFunction {
        double computeCost(Edge edge);
    }
}
