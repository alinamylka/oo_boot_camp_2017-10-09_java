/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

import org.junit.jupiter.api.Test;
import utils.Orderable;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quantity.RatioUnit.CUP;
import static quantity.RatioUnit.FOOT;
import static quantity.RatioUnit.FURLONG;
import static quantity.RatioUnit.GALLON;
import static quantity.RatioUnit.INCH;
import static quantity.RatioUnit.MILE;
import static quantity.RatioUnit.OUNCE;
import static quantity.RatioUnit.PINT;
import static quantity.RatioUnit.QUART;
import static quantity.RatioUnit.TABLESPOON;
import static quantity.RatioUnit.TEASPOON;
import static quantity.RatioUnit.YARD;
import static quantity.Unit.CELSIUS;
import static quantity.Unit.FAHRENHEIT;

// Ensures that Quantity operates correctly
public class TestQuantity {

    @Test
    public void max() {
        assertEquals(TEASPOON.s(4), Orderable.max(TEASPOON.s(4), TABLESPOON.s(1)));
    }

    @Test
    public void maxMixUnits() {
        assertThrows(IllegalArgumentException.class, () -> {
            Orderable.max(TEASPOON.s(4), MILE.s(1));
        });
    }

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
        assertEquals(MILE.s(1), INCH.s(12 * 5280));
    }

    @Test
    public void polymorphism() {
        assertEquals(1, new HashSet<>(
                Arrays.asList(TABLESPOON.s(4), OUNCE.s(2))).size());
        assertTrue(new HashSet<>(
                Arrays.asList(TABLESPOON.s(4), OUNCE.s(2)))
                .contains(CUP.s(0.25)));
    }

    @Test
    public void hash() {
        assertEquals(TABLESPOON.s(4).hashCode(), OUNCE.s(2).hashCode());
        assertEquals(FOOT.s(330).hashCode(), FURLONG.s(0.5).hashCode());
        assertEquals(FAHRENHEIT.s(50).hashCode(), CELSIUS.s(10).hashCode());
    }

    @Test
    public void arithmetic() {
        assertEquals(TEASPOON.s(2), TABLESPOON.s(1).minus(TEASPOON.s(1)));
        assertEquals(QUART.s(0.5), TABLESPOON.s(6).plus(OUNCE.s(13)));
        assertEquals(TABLESPOON.s(-6), TABLESPOON.s(6).negate());

        assertEquals(PINT.s(-0.5), TABLESPOON.s(10).minus(OUNCE.s(13)));
        assertEquals(FOOT.s(-4), INCH.s(24).minus(YARD.s(2)));
    }

    @Test
    public void crossMetricType() {
        assertNotEquals(INCH.s(1), TEASPOON.s(1));
        assertNotEquals(OUNCE.s(4), FOOT.s(2));
        assertNotEquals(CELSIUS.s(1), INCH.s(1));
    }

    @Test
    public void mixedUnitArithmetic() {
        assertThrows(IllegalArgumentException.class, () -> {
            YARD.s(3).minus(TABLESPOON.s(4));
        });
    }

    @Test
    public void temperature() {
        assertEquals(CELSIUS.s(0), FAHRENHEIT.s(32));
        assertEquals(FAHRENHEIT.s(32), CELSIUS.s(0));
        assertEquals(CELSIUS.s(-40), FAHRENHEIT.s(-40));
        assertEquals(FAHRENHEIT.s(-40), CELSIUS.s(-40));
        assertEquals(CELSIUS.s(10), FAHRENHEIT.s(50));
        assertEquals(FAHRENHEIT.s(50), CELSIUS.s(10));
        assertEquals(CELSIUS.s(100), FAHRENHEIT.s(212));
        assertEquals(FAHRENHEIT.s(212), CELSIUS.s(100));
    }
}
