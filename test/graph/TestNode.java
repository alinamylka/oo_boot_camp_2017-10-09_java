package graph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        B.to(A, 5);
        B.to(C, 6).to(D, 7).to(E, 2).to(B, 3).to(F, 4);
        C.to(D, 1);
        C.to(E, 8);
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
    public void path() {
        assertEquals(1, B.path(F).get().hopCount());
        assertEquals(2, B.path(D).get().hopCount());
        assertEquals(3, B.path(E).get().hopCount());
        assertFalse(B.path(G).isPresent());
    }

    @Test
    public void hopCount() {
        assertThrows(IllegalArgumentException.class, () -> B.hopCount(G));
        assertEquals(1, B.hopCount(A));
        assertEquals(3, C.hopCount(F));
    }

    @Test
    public void cost() {
        assertThrows(IllegalArgumentException.class, () -> B.cost(G));
        assertEquals(5d, B.cost(A), DELTA);
        assertEquals(10d, C.cost(F), DELTA);
    }
}