package graph;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

// Understand the connection between the neighbours
public class Edge {

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

    Double hop() {
        return 1.0;
    }

    Double cost() {
        return this.cost;
    }
}
