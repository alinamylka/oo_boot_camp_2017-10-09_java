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
import java.util.function.Function;

import static graph.Edge.COST_STRATEGY;
import static graph.Edge.HOP_STRATEGY;

//Understands neighbours
public class Node {
    private final List<Edge> edges = new ArrayList<>();

    public Node to(Node neighbor, int cost) {
        edges.add(new Edge(neighbor, cost));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination).isPresent();
    }

    public Optional<Integer> hopCount(Node destination) {
        return this.visit(destination, Set.of(), HOP_STRATEGY).map(d -> (int) d.doubleValue());
    }

    public Optional<Double> cost(Node destination) {
        return this.visit(destination, Set.of(), COST_STRATEGY);
    }

    Optional<Double> visit(Node destination, Set<Node> visitedNodes, Function<Edge, Double> strategy) {
        if (this == destination) return Optional.of(0.0);
        if (visitedNodes.contains(this)) return Optional.empty();
        return edges.stream()
                .flatMap(edge -> edge.visit(destination, copyWithThis(visitedNodes), strategy).stream())
                .min(Double::compare);
    }

    private HashSet<Node> copyWithThis(Set<Node> visitedNodes) {
        HashSet<Node> copy = new HashSet<>(visitedNodes);
        copy.add(this);
        return copy;
    }


}
