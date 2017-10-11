package rectangle;
/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

import utils.Orderable;

// Understands a four-sided polygon with sides at right angles
public class Rectangle implements Orderable<Rectangle> {

    private final double length;
    private final double width;

    private Rectangle(double size) {
        this(size, size);
    }

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

    public boolean isSquare() {
        return length == width;
    }

    public static Rectangle squareOf(double side) {
        return new Rectangle(side, side);
    }

    public static Rectangle of(double length, double width) {
        return new Rectangle(length, width);
    }

    @Override
    public int isBetterThen(Rectangle other) {
        return this.area() - other.area() >= 0 ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (Double.compare(rectangle.length, length) != 0) return false;
        return Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
