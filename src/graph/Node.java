package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// understands neighbours
public class Node {

    private final List<Link> neighbours = new ArrayList<>();

    public Node to(Node neighbor, double cost) {
        neighbours.add(new Link(neighbor, cost));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.paths(destination, noVisitedNodes()).size() > 0;
    }

    public Path path(Node destination) {
        return this.paths(destination, noVisitedNodes())
                .stream()
                .min(Comparator.comparingDouble(path -> path.cost()))
                .get();
    }

    public List<Path> paths(Node destination) {
        return this.paths(destination, noVisitedNodes());
    }

    public List<Path> paths(Node destination, Set<Node> visitedNodes) {
        if (destination == this) {
            return Arrays.asList(new Path());
        }

        if (visitedNodes.contains(this)) {
            return Collections.emptyList();
        }

        return neighbours
                .stream()
                .flatMap(neighbour -> neighbour.paths(destination, copyAndThis(visitedNodes)).stream())
                .collect(Collectors.toList());
    }

    public int hopCount(Node destination) {
        Optional<Path> path = findPath(destination, Comparator.comparingInt(Path::hopCount));
        return path.isPresent() ? path.get().hopCount() : 0;
    }

    private Optional<Path> findPath(Node destination, Comparator<Path> comparator) {
        return this.paths(destination).stream().min(comparator);
    }

    public double cost(Node destination) {
        Optional<Path> path = findPath(destination, Comparator.comparingDouble(Path::cost));
        return path.isPresent() ? path.get().cost() : 0;
    }

    private Set<Node> copyAndThis(Set<Node> visitedNodes) {
        visitedNodes.add(this);
        return new HashSet<>(visitedNodes);
    }

    private Set<Node> noVisitedNodes() {
        return new HashSet<>();
    }
}
