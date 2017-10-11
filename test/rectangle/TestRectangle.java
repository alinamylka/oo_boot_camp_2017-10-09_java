/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package rectangle;

import org.junit.jupiter.api.Test;
import utils.Orderable;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Confirms the behavior of a Rectangle
public class TestRectangle {

    @Test
    public void max() {
        assertEquals(Orderable.max(Rectangle.squareOf(4.0), Rectangle.squareOf(2.0))
                , Rectangle.squareOf(4.0));

    }

    @Test
    public void sort() {
        assertEquals(Orderable.sort(Rectangle.squareOf(4.0), Rectangle.squareOf(2.0))
            , Arrays.asList(Rectangle.squareOf(2.0), Rectangle.squareOf(4.0)));

        assertNotEquals(Orderable.sort(Rectangle.squareOf(4.0), Rectangle.squareOf(2.0))
                , Arrays.asList(Rectangle.squareOf(4.0), Rectangle.squareOf(2.0)));

        assertEquals(Orderable.sort(Rectangle.of(2.0, 8.0), Rectangle.of(4.0, 4.0))
                , Arrays.asList(Rectangle.of(2.0, 8.0), Rectangle.of(4.0, 4.0)));
    }

    @Test
    public void area() {
        assertEquals(24.0, Rectangle.of(4.0, 6.0).area());
        assertEquals(25.0, Rectangle.squareOf(5.0).area());
    }

    @Test
    public void perimeter() {
        assertEquals(20, Rectangle.of(4, 6).perimeter());
        assertEquals(20, Rectangle.squareOf(5).perimeter());
    }

    @Test
    public void isSquare() {
        assertTrue(Rectangle.squareOf(4).isSquare());
        assertFalse(Rectangle.of(4, 6).isSquare());
        assertTrue(Rectangle.of(4, 4).isSquare());
    }


    @Test
    public void validParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            Rectangle.of(0, 6);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Rectangle.of(4, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Rectangle.squareOf(0);
        });
    }
}
