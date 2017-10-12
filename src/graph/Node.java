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
import java.util.function.BiFunction;

//Understands neighbours
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
        return this.visit(destination, new HashSet<>(), Edge::hop);
    }

    public Optional<Integer> cost(Node destination) {
        return this.visit(destination, new HashSet<>(), Edge::cost);
    }

    Optional<Integer> visit(Node destination, Set<Node> visitedNodes, BiFunction<Edge, Integer, Integer> strategy) {
        if (this == destination) return Optional.of(NO_HOP);
        if (visitedNodes.contains(this)) return Optional.empty();
        visitedNodes.add(this);
        return neighbors.stream()
                .flatMap(n -> n.visit(destination, new HashSet<>(visitedNodes), strategy).stream())
                .min(Integer::compare);
    }


}
