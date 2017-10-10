/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static quantity.Quantity.*;

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
    }
}
