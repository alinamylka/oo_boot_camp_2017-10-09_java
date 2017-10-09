/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package probability;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Chance operates correctly
public class TestChance {

    private final static Chance IMPOSSIBLE = new Chance(0.0);
    private final static Chance UNLIKELY = new Chance(0.25);
    private final static Chance EQUALLY_LIKELY = new Chance(0.50);
    private final static Chance LIKELY = new Chance(0.75);
    private final static Chance CERTAIN = new Chance(1.0);

    @Test
    public void equality() {
        assertEquals(LIKELY, new Chance(0.75));
        assertNotEquals(LIKELY, new Chance(0.25));
        assertNotEquals(LIKELY, new Object());
        assertNotEquals(LIKELY, null);
    }

    @Test
    public void polymorphism() {
        assertEquals(1, new HashSet<Chance>(
                Arrays.asList(LIKELY, new Chance(0.75))).size());
        assertTrue(new HashSet<Chance>(
                Arrays.asList(LIKELY, new Chance(0.75)))
                .contains(new Chance(0.75)));
    }

    @Test
    public void hash() {
        assertEquals(LIKELY.hashCode(), new Chance(0.75).hashCode());
    }


}
