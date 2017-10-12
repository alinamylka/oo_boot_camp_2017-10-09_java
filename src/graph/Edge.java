package graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

// Understand the connection between the neighbours
public class Edge {

    private static final int ONE_HOP = 1;
    private final Node neighbor;
   public final int cost;

    public Edge(Node neighbor, int cost) {

        this.neighbor = neighbor;
        this.cost = cost;
    }

    Optional<Integer> hopCount(Node destination, Set<Node> visitedNodes) {
        return neighbor.hopCount(destination, new HashSet<>(visitedNodes)).map(computeHops());
    }

    private Function<Integer, Integer> computeHops() {
        return n -> n + ONE_HOP;
    }

    Optional<Integer> cost(Node destination, Set<Node> visitedNodes, Function<Integer, Integer> strategy) {
        return neighbor.cost(destination, new HashSet<>(visitedNodes)).map(strategy);
    }

    public Function<Integer, Integer> computeCost() {
        return cost -> cost + this.cost;
    }

}
