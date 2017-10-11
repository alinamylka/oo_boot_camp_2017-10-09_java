/*
 * Copyright (c) 2017 by Fred George
 * May be used freely except for training; license required for training.
 */

package quantity;

// Understands a specific ratio measurement
public class RatioQuantity extends Quantity<RatioUnit> {

    RatioQuantity(double amount, RatioUnit unit) {
        super(amount, unit);
    }

    public RatioQuantity plus(RatioQuantity other) {
        return new RatioQuantity(this.amount + this.convertedAmount(other), this.unit);
    }

    public RatioQuantity minus(RatioQuantity other) {
        return this.plus(other.negate());
    }

    public RatioQuantity negate() {
        return new RatioQuantity(-amount, unit);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "amount=" + amount +
                ", unit=" + unit +
                '}';
    }
}
