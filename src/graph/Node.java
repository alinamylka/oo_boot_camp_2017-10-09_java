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
    private double UNREACHABLE = Double.POSITIVE_INFINITY;

    public Node to(Node neighbor, int cost) {
        edges.add(new Edge(neighbor, cost));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return hopCount(destination).isPresent();
    }

    public Optional<Integer> hopCount(Node destination) {
        double result = findDestination(destination, Set.of(), HOP_STRATEGY);
        return result == UNREACHABLE ? Optional.empty() : Optional.of((int) result);
    }

    public Optional<Double> cost(Node destination) {
        double result = findDestination(destination, Set.of(), COST_STRATEGY);
        return result == UNREACHABLE ? Optional.empty() : Optional.of(result);
    }

    double findDestination(Node destination, Set<Node> visitedNodes, Function<Edge, Double> strategy) {
        if (this == destination) return 0.0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return edges.stream()
                .mapToDouble(edge -> edge.visit(destination, copyWithThis(visitedNodes), strategy))
                .min().orElse(UNREACHABLE);
    }

    private Set<Node> copyWithThis(Set<Node> visitedNodes) {
        Set<Node> copy = new HashSet<>(visitedNodes);
        copy.add(this);
        return copy;
    }


}
