package graph;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

// Understand the connection between the neighbours
public class Edge {

    private static final int ONE_HOP = 1;
    private final Node neighbor;
    private final int cost;

    public Edge(Node neighbor, int cost) {
        this.neighbor = neighbor;
        this.cost = cost;
    }

    Optional<Integer> visit(Node destination, Set<Node> visitedNodes, BiFunction<Edge, Integer, Integer> strategy) {
        return neighbor.visit(destination, visitedNodes, strategy)
                .map(r -> strategy.apply(this, r));
    }

    Integer hop(Integer hops) {
        return hops + ONE_HOP;
    }

    Integer cost(Integer cost) {
        return cost + this.cost;
    }
}
