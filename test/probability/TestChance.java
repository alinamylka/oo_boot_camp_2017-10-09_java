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

    @Test
    public void equality() {
        assertEquals(new Chance(0.75), new Chance(0.75));
        assertNotEquals(new Chance(0.75), new Chance(0.25));
        assertNotEquals(new Chance(0.75), new Object());
        assertNotEquals(new Chance(0.75), null);
    }

    @Test
    public void polymorphism() {
        assertEquals(1, new HashSet<Chance>(
                Arrays.asList(new Chance(0.75), new Chance(0.75))).size());
        assertTrue(new HashSet<Chance>(
                Arrays.asList(new Chance(0.75), new Chance(0.75)))
                .contains(new Chance(0.75)));
    }

    @Test
    public void hash() {
        assertEquals(new Chance(0.75).hashCode(), new Chance(0.75).hashCode());
    }


}
