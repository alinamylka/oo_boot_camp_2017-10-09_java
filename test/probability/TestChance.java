/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package probability;

import org.junit.jupiter.api.Test;
import rectangle.Rectangle;
import utils.Orderable;

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
        assertEquals(1, new HashSet<>(
                Arrays.asList(LIKELY, new Chance(0.75))).size());
        assertTrue(new HashSet<>(
                Arrays.asList(LIKELY, new Chance(0.75)))
                .contains(new Chance(0.75)));
    }

    @Test
    public void hash() {
        assertEquals(LIKELY.hashCode(), new Chance(0.75).hashCode());
        assertEquals(new Chance(0.3).hashCode(), new Chance(0.7).not().hashCode());
    }

    @Test
    public void not() {
        assertEquals(UNLIKELY, LIKELY.not());
        assertEquals(LIKELY, LIKELY.not().not());
        assertEquals(CERTAIN, IMPOSSIBLE.not());
        assertEquals(EQUALLY_LIKELY, EQUALLY_LIKELY.not());
        assertEquals(new Chance(0.3), new Chance(0.7).not());
    }

    @Test
    public void and() {
        assertEquals(UNLIKELY, EQUALLY_LIKELY.and(EQUALLY_LIKELY));
        assertEquals(new Chance(0.1875), LIKELY.and(UNLIKELY));
        assertEquals(LIKELY.and(UNLIKELY), UNLIKELY.and(LIKELY));
        assertEquals(IMPOSSIBLE, LIKELY.and(IMPOSSIBLE));
        assertEquals(LIKELY, CERTAIN.and(LIKELY));
    }

    @Test
    public void or() {
        assertEquals(LIKELY, EQUALLY_LIKELY.or(EQUALLY_LIKELY));
        assertEquals(new Chance(0.8125), LIKELY.or(UNLIKELY));
        assertEquals(LIKELY.or(UNLIKELY), UNLIKELY.or(LIKELY));
        assertEquals(LIKELY, LIKELY.or(IMPOSSIBLE));
        assertEquals(CERTAIN, CERTAIN.or(LIKELY));
    }

    @Test
    public void validParameters() {
        assertThrows(IllegalArgumentException.class, () -> { new Chance(1.1); });
        assertThrows(IllegalArgumentException.class, () -> { new Chance(-0.1); });
    }

    @Test
    public void max() {
        assertEquals(Orderable.max(new Chance(0.8125),
                new Chance(0.1),
                new Chance(0.5))
                , new Chance(0.8125));
    }

}
