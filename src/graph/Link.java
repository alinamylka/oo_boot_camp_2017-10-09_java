package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// understands relations between neighbours
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
