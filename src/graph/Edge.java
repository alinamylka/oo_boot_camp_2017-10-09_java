package graph;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

// Understand the connection between the neighbours
public class Edge {

    private final Node neighbor;
    private final int cost;

    public Edge(Node neighbor, int cost) {
        this.neighbor = neighbor;
        this.cost = cost;
    }

    Optional<Double> visit(Node destination, Set<Node> visitedNodes, BiFunction<Edge, Double, Double> strategy) {
        return neighbor.visit(destination, visitedNodes, strategy)
                .map(r -> strategy.apply(this, r));
    }

    Double hop(Double hops) {
        return hops + 1;
    }

    Double cost(Double cost) {
        return cost + this.cost;
    }
}
