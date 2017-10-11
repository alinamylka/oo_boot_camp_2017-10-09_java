package graph;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestNode {
    private final static Node A, B, C, D, E, F, G;

    static {
        A = new Node();
        B = new Node();
        C = new Node();
        D = new Node();
        E = new Node();
        F = new Node();
        G = new Node();

        B.to(A);
        B.to(C).to(D).to(E).to(B).to(F);
        C.to(D);
        C.to(E);
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
}