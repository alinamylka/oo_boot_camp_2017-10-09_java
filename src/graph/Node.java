package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> neighbours = new ArrayList<>();

    public Node to(Node neighbor) {
        neighbours.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.canReach(destination, noVisitedNodes());
    }

    private boolean canReach(Node destination, List<Node> visitedNodes) {
        if (this == destination) return true;
        if (visitedNodes.contains(this)) return false;
        visitedNodes.add(this);
        for (Node node : neighbours)
            if (node.canReach(destination, visitedNodes)) return true;
        return false;
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
