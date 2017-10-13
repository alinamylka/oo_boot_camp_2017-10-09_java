package graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNode {
    private final static Node A, B, C, D, E, F, G;
    private static final double DELTA = 0.00001d;

    static {
        A = new Node("A");
        B = new Node("B");
        C = new Node("C");
        D = new Node("D");
        E = new Node("E");
        F = new Node("F");
        G = new Node("G");

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
    public void paths() {
        assertEquals(Arrays.asList(0.0), B.paths(B).stream().map(Path::cost).sorted().collect(Collectors.toList()));
        assertEquals(new ArrayList<>(), B.paths(G));
        assertEquals(Arrays.asList(6.0), B.paths(C).stream().map(Path::cost).sorted().collect(Collectors.toList()));
        assertEquals(Arrays.asList(3.0, 8d, 9d), C.paths(E).stream().map(Path::cost).sorted().collect(Collectors.toList()));
        assertEquals(Arrays.asList(10d, 15d, 16d), C.paths(F).stream().map(Path::cost).sorted().collect(Collectors.toList()));
        assertEquals(Arrays.asList(4d, 16d, 21d, 22d), B.paths(F).stream().map(Path::cost).sorted().collect(Collectors.toList()));
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