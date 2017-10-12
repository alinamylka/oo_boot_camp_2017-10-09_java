/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Node {

    private static final int NO_HOP = 0;
    private final List<Edge> neighbors = new ArrayList<>();

    public Node to(Node neighbor, int cost) {
        neighbors.add(new Edge(neighbor, cost));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination).isPresent();
    }

    public Optional<Integer> hopCount(Node destination) {
        return this.hopCount(destination, new HashSet<>());
    }

    Optional<Integer> hopCount(Node destination, Set<Node> visitedNodes) {
        if (this == destination) return Optional.of(NO_HOP);
        if (visitedNodes.contains(this)) return Optional.empty();
        visitedNodes.add(this);
        return neighbors.stream()
                .flatMap(n -> n.hopCount(destination, visitedNodes).stream())
                .min(Integer::compare);
    }

    public Optional<Integer> cost(Node destination) {
        return this.cost(destination, new HashSet<>());
    }

    Optional<Integer> cost(Node destination, Set<Node> visitedNodes) {
        if (this == destination) return Optional.of(NO_HOP);
        if (visitedNodes.contains(this)) return Optional.empty();
        visitedNodes.add(this);
        return neighbors.stream()
                .flatMap(edge -> edge.cost(destination, visitedNodes, cost -> cost + edge.cost).stream())
                .min(Integer::compare);
    }


}
