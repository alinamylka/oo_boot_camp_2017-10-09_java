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

    public static final Unit CELSIUS = new Unit();
    public static final Unit FAHRENHEIT = new Unit(5 / 9.0, 32, CELSIUS);

    private final double baseUnitRatio;
    private final double offset;
    private final Unit baseUnit;

    private Unit() {
        this.baseUnitRatio = 1.0;
        this.offset = 0.0;
        this.baseUnit = this;
    }

    private Unit(double relativeRatio, Unit relativeUnit) {
        this(relativeRatio, 0.0, relativeUnit);
    }

    private Unit(double relativeRatio, double offset, Unit relativeUnit) {
        this.baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
        this.offset = offset;
        this.baseUnit = relativeUnit.baseUnit;
    }

    double convertedAmount(double otherAmount, Unit other) {
        if (!this.isCompatible(other)) throw new IllegalArgumentException("Incompatible unit types");
        return (otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset;
    }

    /*int hashCode(double amount) {
        return Objects.hash(Math.round(amount * baseUnitRatio * 10000000d) / 10000000d);
    }*/

    int hashCode(double amount) {
        return Objects.hash(amount * baseUnitRatio );
    }

    public Quantity s(double amount) {
        return new Quantity(amount, this);
    }

    public boolean isCompatible(Unit other) {
        return this.baseUnit == other.baseUnit;
    }
}
