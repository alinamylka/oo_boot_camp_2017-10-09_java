/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {

    private final List<Node> neightbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neightbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.canReach(destination, noVisitedNodes());
    }

    private boolean canReach(Node destination, Set<Node> visitedNodes) {
        if (this == destination) return true;
        if (visitedNodes.contains(this)) return false;
        visitedNodes.add(this);

        return neightbors.stream()
                .filter(n -> n.canReach(destination, visitedNodes))
                .findFirst()
                .isPresent();
    }

    private Set<Node> noVisitedNodes() {
        return new HashSet<>();
    }
}
