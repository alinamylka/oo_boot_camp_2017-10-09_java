/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package probability;

import java.util.Objects;

// Understands the likelihood of something specific happening
public class Chance {

    private final static double CERTAIN_FRACTION = 1.0;
    private final static double TOLERANCE = 0.0000001;

    private final double fraction;

    public Chance(double likelihoodAsFraction) {
        fraction = likelihoodAsFraction;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        return this.equals((Chance)other);
    }

    private boolean equals(Chance other) {
        return Math.abs(this.fraction - other.fraction) < TOLERANCE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(fraction * 100000d) / 100000d);
    }

    public Chance not() {
        return new Chance(CERTAIN_FRACTION - fraction);
    }
}
