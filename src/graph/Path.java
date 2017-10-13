package graph;

import java.util.LinkedList;

// understands a way from one node to another node
public class Path {
    private final LinkedList<Link> nodes;

    public Path() {
        this.nodes = new LinkedList<>();
    }

    public void add(Link node) {
        this.nodes.push(node);
    }

    public int hopCount() {
        return nodes.size();
    }

    public double cost() {
        return Link.totalCost(nodes);
    }
}
