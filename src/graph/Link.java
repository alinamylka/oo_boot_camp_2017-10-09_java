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

    public List<Path> paths(Node destination, Set<Link> visitedLinks) {
        List<Path> paths = other.paths(destination, visitedLinks);
        paths.forEach(p -> p.prepend(this));
        return paths;
    }

    public static double totalCost(LinkedList<Link> nodes) {
        return nodes.stream().mapToDouble(Link::cost).sum();
    }

    private double cost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Link{" +
                "other=" + other +
                ", cost=" + cost +
                '}';
    }
}
