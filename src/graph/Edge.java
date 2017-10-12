package graph;

import java.util.Optional;
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

    Optional<Double> visit(Node destination, Set<Node> visitedNodes, Function<Edge, Double> strategy) {
        return neighbor.visit(destination, visitedNodes, strategy)
                .map(r -> r + strategy.apply(this));
    }
}
