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
        assertEquals(MILE.s(1), INCH.es(12 * 5280));
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
        assertEquals(FOOT.s(330).hashCode(), FURLONG.s(0.5).hashCode());
    }

    @Test
    public void arithmetic() {
        assertEquals(QUART.s(0.5), TABLESPOON.s(6).plus(OUNCE.s(13)));
        assertEquals(TABLESPOON.s(-6), TABLESPOON.s(6).negate());
        assertEquals(PINT.s(-0.5), TABLESPOON.s(10).minus(OUNCE.s(13)));
        assertEquals(FOOT.s(-4), INCH.es(24).minus(YARD.s(2)));
    }

    @Test
    public void crossMetricType()  {
        assertNotEquals(INCH.es(1), TEASPOON.s(1));
        assertNotEquals(OUNCE.s(4), FOOT.s(2));
    }

    @Test
    public void mixedUnitArithmetic() {
        assertThrows(IllegalArgumentException.class, () -> {
            YARD.s(3).minus(TABLESPOON.s(4));
        });
    }

}
