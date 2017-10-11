/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;


// Understands a specific measurement
public class Quantity<T extends Unit> {

    private final static double TOLERANCE = 0.0000001;

    protected final double amount;
    protected final T unit;

    Quantity(double amount, T unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        return this.equals((Quantity) other);
    }

    private boolean equals(Quantity other) {
        if (!this.isCompatible(other)) return false;
        return Math.abs(this.amount - this.convertedAmount(other)) < TOLERANCE;
    }

    private boolean isCompatible(Quantity other) {
        return this.unit.isCompatible(other.unit);
    }

    protected double convertedAmount(Quantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "amount=" + amount +
                ", unit=" + unit +
                '}';
    }
}
