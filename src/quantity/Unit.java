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

    private final double baseUnitRatio;

    public Unit() {
        this.baseUnitRatio = 1.0;
    }

    public Unit(double relativeRatio, Unit relativeUnit) {
        this.baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }

    public double convertedAmount(double otherAmount, Unit other) {
        return otherAmount * other.baseUnitRatio / this.baseUnitRatio;
    }

    public int hashCode(double amount) {
        return Objects.hash(Math.round(amount * baseUnitRatio * 10000000d) / 10000000d);
    }

}
