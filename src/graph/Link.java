package graph;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

// understands relations between neighbours
public class Link {
    private Node other;
    private double cost;

    public Link(Node other, double cost) {
        this.other = other;
        this.cost = cost;
    }

    public Optional<Path> path(Node destination, Set<Node> visitedNodes) {
        Optional<Path> path = other.path(destination, visitedNodes, Path::cost);
        path.ifPresent(p -> p.prepend(this));
        return path;
    }

    @Override
    public String toString() {
        return other.toString();
    }

    public static double totalCost(LinkedList<Link> nodes) {
        return nodes.stream().mapToDouble(Link::cost).sum();
    }

    private double cost() {
        return cost;
    }
}
