/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package rectangle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private final static double EPSILON = 0.00001;

    @Test
    public void testArea() {

        assertEquals(12, new Rectangle(3, 4).area(), EPSILON);
    }

}
