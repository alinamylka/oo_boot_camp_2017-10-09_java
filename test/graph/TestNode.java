package graph;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void hopCount() {
        assertEquals(0, B.hopCount(B).get().intValue());
        assertEquals(1, B.hopCount(A).get().intValue());
        assertEquals(1, B.hopCount(F).get().intValue());
        assertEquals(2, B.hopCount(D).get().intValue());

        assertFalse(A.hopCount(B).isPresent());
        assertFalse(B.hopCount(G).isPresent());
        assertFalse(G.hopCount(B).isPresent());

        assertTrue(Arrays.asList(3, 4).contains(C.hopCount(F).get().intValue()));

        assertFalse(A.hopCount(B).isPresent());
        assertFalse(B.hopCount(G).isPresent());
        assertFalse(G.hopCount(B).isPresent());
    }


}