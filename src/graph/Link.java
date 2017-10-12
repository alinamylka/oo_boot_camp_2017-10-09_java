package graph;

import java.util.List;
import java.util.Set;

public class Link {
    private Node other;
    private double cost;

    public Link(Node other, double cost) {
        this.other = other;
        this.cost = cost;
    }

    public List<Path> paths(Node destination, Set<Node> visitedNodes) {
        List<Path> paths = other.paths(destination, visitedNodes);
        paths.forEach(path -> path.add(this));
        return paths;
    }

    public double cost() {
        return cost;
    }

    @Override
    public String toString() {
        return other.toString();
    }
}
