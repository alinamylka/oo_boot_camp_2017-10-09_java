/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static quantity.Unit.*;

// Ensures that Quantity operates correctly
public class TestQuantity {

    @Test
    public void equalityOfSameUnit() {
        assertEquals(TABLESPOON.s(4), TABLESPOON.s(4));
        assertNotEquals(TABLESPOON.s(4), TABLESPOON.s(3));
        assertNotEquals(TABLESPOON.s(4), new Object());
        assertNotEquals(TABLESPOON.s(4), null);
    }

    @Test
    public void equalityOfDifferentUnit() {
        assertNotEquals(TABLESPOON.s(4), OUNCE.s(4));
        assertEquals(TABLESPOON.s(4), OUNCE.s(2));
        assertEquals(GALLON.s(1), TEASPOON.s(768));
    }

    @Test
    public void polymorphism() {
        assertEquals(1, new HashSet<Quantity>(
                Arrays.asList(TABLESPOON.s(4), OUNCE.s(2))).size());
        assertTrue(new HashSet<Quantity>(
                Arrays.asList(TABLESPOON.s(4), OUNCE.s(2)))
                .contains(CUP.s(0.25)));
    }

    @Test
    public void hash() {
        assertEquals(TABLESPOON.s(4).hashCode(), OUNCE.s(2).hashCode());
    }
}
