/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package rectangle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Confirms the behavior of a Rectangle
public class TestRectangle {
    @Test
    public void area() {
        assertEquals(24.0, new Rectangle(4.0, 6.0).area());
        assertEquals(25.0, Rectangle.square(5.0).area());
    }

    @Test
    public void perimeter() {
        assertEquals(20, new Rectangle(4, 6).perimeter());
        assertEquals(20, Rectangle.square(5).perimeter());
    }

    @Test
    public void validParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(0, 6);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(4, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Rectangle.square(0);
        });
    }
}
