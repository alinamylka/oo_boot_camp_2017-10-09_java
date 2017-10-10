/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

import java.util.Objects;

// Understands a specific metric
public class Unit {

    public static final Unit TEASPOON = new Unit();
    public static final Unit TABLESPOON = new Unit(3, TEASPOON);
    public static final Unit OUNCE = new Unit(2, TABLESPOON);
    public static final Unit CUP = new Unit(8, OUNCE);
    public static final Unit PINT = new Unit(2, CUP);
    public static final Unit QUART = new Unit(2, PINT);
    public static final Unit GALLON = new Unit(4, QUART);

    public static final Unit INCH = new Unit();
    public static final Unit FOOT = new Unit(12, INCH);
    public static final Unit YARD = new Unit(3, FOOT);
    public static final Unit FURLONG = new Unit(220, YARD);
    public static final Unit MILE = new Unit(8, FURLONG);

    private final double baseUnitRatio;
    private final Unit baseUnit;

    private Unit() {
        this.baseUnitRatio = 1.0;
        this.baseUnit = this;
    }

    private Unit(double relativeRatio, Unit relativeUnit) {
        this.baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
        this.baseUnit = relativeUnit.baseUnit;
    }

    double convertedAmount(double otherAmount, Unit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    int hashCode(double amount) {
        return Objects.hash(Math.round(amount * baseUnitRatio * 10000000d) / 10000000d);
    }

    public Quantity s(double amount) {
        return new Quantity(amount, this);
    }

    public Quantity es(double amount) {
        return this.s(amount);
    }

    public boolean isCompatible(Unit other) {
        return this.baseUnit == other.baseUnit;
    }
}
