package graph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestNode {
    private final static Node A, B, C, D, E, F, G;
    private static final double DELTA = 0.00001d;

    static {
        A = new Node();
        B = new Node();
        C = new Node();
        D = new Node();
        E = new Node();
        F = new Node();
        G = new Node();

        B.to(A, 10);
        B.to(C, 1).to(D, 2).to(E, 1).to(B, 2).to(F, 3);
        C.to(D, 5);
        C.to(E, 2);
    }

    @Test
    public void canReach() {
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(A));
        assertTrue(B.canReach(F));
        assertTrue(B.canReach(D));
        assertTrue(C.canReach(F));
        assertFalse(G.canReach(B));
        assertFalse(A.canReach(B));
        assertFalse(B.canReach(G));
    }
    @Test
    public void paths() {
        assertEquals(1, B.paths(F).size());
        assertEquals(2, B.paths(D).size());
        assertEquals(3, B.paths(E).size());
        assertEquals(0, B.paths(G).size());
    }

    @Test
    public void hopCount() {
        assertEquals(0, B.hopCount(G));
        assertEquals(1, B.hopCount(A));
        assertEquals(3, C.hopCount(F));
    }

    @Test
    public void cost() {
        assertEquals(0d, B.cost(G), DELTA);
        assertEquals(10d, B.cost(A), DELTA);
        assertEquals(7d, C.cost(F), DELTA);
    }
}