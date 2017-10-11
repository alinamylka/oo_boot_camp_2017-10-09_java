package rectangle;

/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package rectangle;

// Understands a four-sided polygon with sides at right angles
public class Rectangle {

    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        if (length <= 0.0 || width <= 0.0)
            throw new IllegalArgumentException("Dimensions must be > 0");
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }

    public double perimeter() {
        return 2 * (length + width);
    }

    public static Rectangle square(double side) {
        return new Rectangle(side, side);
    }
}
