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
        return path(destination).isPresent();
    }


    public Optional<Path> path(Node destination) {
        return path(destination, Path::cost);
    }

    Optional<Path> path(Node destination, ToDoubleFunction<Path> strategy) {
        return paths(destination, noVisitedLinks())
                .stream()
                .min(Comparator.comparingDouble(strategy));
    }

    public int hopCount(Node destination) {
        return path(destination, Path::hopCount)
                .map(Path::hopCount)
                .orElseThrow(() -> new IllegalArgumentException("Destination is not reachable"));
    }


    public double cost(Node destination) {
        return path(destination)
                .map(Path::cost)
                .orElseThrow(() -> new IllegalArgumentException("Destination is not reachable"));
    }

    public List<Path> paths(Node destination) {
        return paths(destination, noVisitedLinks());
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

    private HashSet<Link> noVisitedLinks() {
        return new HashSet<>();
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                '}';
    }
}
