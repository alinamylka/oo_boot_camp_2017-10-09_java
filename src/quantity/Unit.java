/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

import java.util.Objects;

// Understands a specific metric
public class Unit {

    public static final Unit CELSIUS = new Unit();
    public static final Unit FAHRENHEIT = new Unit(5 / 9.0, 32, CELSIUS);

    private final double baseUnitRatio;
    private final double offset;
    private final Unit baseUnit;
    Unit() {
        this.baseUnitRatio = 1.0;
        this.offset = 0.0;
        this.baseUnit = this;
    }

    Unit(double relativeRatio, double offset, Unit relativeUnit) {
        this.baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
        this.offset = offset;
        this.baseUnit = relativeUnit.baseUnit;
    }

    double convertedAmount(double otherAmount, Unit other) {
        if (!this.isCompatible(other)) throw new IllegalArgumentException("Incompatible unit types");
        return (otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset;
    }

    int hashCode(double amount) {
        return Objects.hash(Math.round((amount - offset) * baseUnitRatio * 10000000d) / 10000000d);
    }

    public Quantity s(double amount) {
        return new Quantity(amount, this);
    }

    public boolean isCompatible(Unit other) {
        return this.baseUnit == other.baseUnit;
    }

}
