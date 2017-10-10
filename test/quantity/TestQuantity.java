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
        assertEquals(new Quantity(4, TABLESPOON), new Quantity(4, TABLESPOON));
        assertNotEquals(new Quantity(4, TABLESPOON), new Quantity(3, TABLESPOON));
        assertNotEquals(new Quantity(4, TABLESPOON), new Object());
        assertNotEquals(new Quantity(4, TABLESPOON), null);
    }

    @Test
    public void equalityOfDifferentUnit() {
        assertNotEquals(new Quantity(4, TABLESPOON), new Quantity(4, OUNCE));
        assertEquals(new Quantity(4, TABLESPOON), new Quantity(2, OUNCE));
        assertEquals(new Quantity(1, GALLON), new Quantity(768, TEASPOON));
    }

    @Test
    public void polymorphism() {
        assertEquals(1, new HashSet<Quantity>(
                Arrays.asList(new Quantity(4, TABLESPOON), new Quantity(2, OUNCE))).size());
        assertTrue(new HashSet<Quantity>(
                Arrays.asList(new Quantity(4, TABLESPOON), new Quantity(2, OUNCE)))
                .contains(new Quantity(0.25, CUP)));
    }

    @Test
    public void hash() {
        assertEquals(new Quantity(4, TABLESPOON).hashCode(), new Quantity(2, OUNCE).hashCode());
    }
}
