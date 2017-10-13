package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

// understands neighbours
public class Node {

    private final List<Link> neighbours = new ArrayList<>();
    private final String label;

    public Node(String label) {
        this.label = label;
    }

    public Node to(Node neighbor, double cost) {
        neighbours.add(new Link(neighbor, cost));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.path(destination, noVisitedNodes(), Path::cost).isPresent();
    }


    public Optional<Path> path(Node destination) {
        return this.path(destination, Path::cost);
    }

    Optional<Path> path(Node destination, ToDoubleFunction<Path> strategy) {
        return this.path(destination, noVisitedNodes(), strategy);
    }

    Optional<Path> path(Node destination, Set<Node> visitedNodes, ToDoubleFunction<Path> strategy) {
        if (destination == this) {
            return Optional.of(Path.of());
        }

        if (visitedNodes.contains(this)) {
            return Optional.empty();
        }

        return neighbours
                .stream()
                .flatMap(neighbour -> neighbour.path(destination, copyAndThis(visitedNodes)).stream())
                .min(Comparator.comparingDouble(strategy));
    }

    public int hopCount(Node destination) {
        return path(destination, Path::hopCount)
                .map(Path::hopCount)
                .orElseThrow(() -> new IllegalArgumentException("Destination is not reachable"));
    }


    public double cost(Node destination) {
        return path(destination, Path::cost)
                .map(Path::cost)
                .orElseThrow(() -> new IllegalArgumentException("Destination is not reachable"));
    }

    private Set<Node> copyAndThis(Set<Node> visitedNodes) {
        visitedNodes.add(this);
        return new HashSet<>(visitedNodes);
    }

    private Set<Node> noVisitedNodes() {
        return new HashSet<>();
    }

    public List<Path> paths(Node destination) {
        return paths(destination, new HashSet<>());
    }

    List<Path> paths(Node destination, Set<Link> visitedLinks) {
        if (destination == this) {
            return Arrays.asList(Path.of());
        }

        return neighbours
                .stream()
                .filter(neighbour -> !visitedLinks.contains(neighbour))
                .flatMap(neighbour -> neighbour.paths(destination, copyAndNeighbour(visitedLinks, neighbour)).stream())
                .collect(Collectors.toList());
    }

    private Set<Link> copyAndNeighbour(Set<Link> visitedLinks, Link neighbour) {
        Set<Link> visitedLinksCopy = new HashSet<>(visitedLinks);
        visitedLinksCopy.add(neighbour);
        return visitedLinksCopy;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                '}';
    }
}
