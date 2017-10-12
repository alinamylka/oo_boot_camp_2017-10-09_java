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

    public static final int ONE_HOP = 1;
    public static final int NO_HOP = 0;
    private final List<Node> neightbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neightbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination).isPresent();
    }

    public Optional<Integer> hopCount(Node destination) {
        return this.hopCount(destination, noVisitedNodes());
    }

    private Optional<Integer> hopCount(Node destination, Set<Node> visitedNodes) {
        if (this == destination) return Optional.of(NO_HOP);
        if (visitedNodes.contains(this)) return Optional.empty();
        visitedNodes.add(this);
        return neightbors.stream()
                .flatMap(n -> n.hopCount(destination, visitedNodes).stream())
                .map(n -> n + ONE_HOP)
                .findAny();
    }

    private Set<Node> noVisitedNodes() {
        return new HashSet<>();
    }
}
