package rectangle;

import utils.Orderable;

public class Rectangle implements Orderable<Rectangle> {

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
}