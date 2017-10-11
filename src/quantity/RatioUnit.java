package quantity;

// Understands a ratio metric
public class RatioUnit extends Unit {

    public static final RatioUnit TEASPOON = new RatioUnit();
    public static final RatioUnit TABLESPOON = new RatioUnit(3, TEASPOON);
    public static final RatioUnit OUNCE = new RatioUnit(2, TABLESPOON);
    public static final RatioUnit CUP = new RatioUnit(8, OUNCE);
    public static final RatioUnit PINT = new RatioUnit(2, CUP);
    public static final RatioUnit QUART = new RatioUnit(2, PINT);
    public static final RatioUnit GALLON = new RatioUnit(4, QUART);
    public static final RatioUnit INCH = new RatioUnit();
    public static final RatioUnit FOOT = new RatioUnit(12, INCH);
    public static final RatioUnit YARD = new RatioUnit(3, FOOT);
    public static final RatioUnit FURLONG = new RatioUnit(220, YARD);
    public static final RatioUnit MILE = new RatioUnit(8, FURLONG);

    public RatioUnit() {
        super();
    }

    public RatioUnit(int amount, RatioUnit ratioUnit) {
        super(amount, ratioUnit);
    }

    @Override
    public RatioQuantity s(double amount) {
        return new RatioQuantity(amount, this);
    }
}
