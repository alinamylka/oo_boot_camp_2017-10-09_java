package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// understands neighbours
public class Node {

    private final List<Link> neighbours = new ArrayList<>();

    public Node to(Node neighbor, double cost) {
        neighbours.add(new Link(neighbor, cost));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.path(destination, noVisitedNodes()).isPresent();
    }

    public Optional<Path> path(Node destination) {
        return this.path(destination, noVisitedNodes());
    }

    public Optional<Path> path(Node destination, Set<Node> visitedNodes) {
        if (destination == this) {
            return Optional.of(new Path());
        }

        if (visitedNodes.contains(this)) {
            return Optional.empty();
        }

        return neighbours
                .stream()
                .flatMap(neighbour -> neighbour.path(destination, copyAndThis(visitedNodes)).stream())
                .min(Comparator.comparingDouble(Path::cost));
    }

    public int hopCount(Node destination) {
        return path(destination)
                .map(Path::hopCount)
                .orElseThrow(IllegalArgumentException::new);
    }


    public double cost(Node destination) {
        return path(destination)
                .map(Path::cost)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Set<Node> copyAndThis(Set<Node> visitedNodes) {
        visitedNodes.add(this);
        return new HashSet<>(visitedNodes);
    }

    private Set<Node> noVisitedNodes() {
        return new HashSet<>();
    }
}
