package model;
/**
 * A denomination represents a bill (money bill like 20 peso bill).
 * It includes all the denomination in the Philippines, and a double value for which the bills represent.
 */

public enum Denomination {
    ONE_THOUSAND(1000.0),
    FIVE_HUNDRED(500.0),
    TWO_HUNDRED(200.0),
    ONE_HUNDRED(100.0),
    FIFTY(50.0),
    TWENTY(20.0),
    TEN(10.0),
    FIVE(5.0),
    ONE(1.0);

    private double value;
    private Denomination(Double value){
        this.value = value;
    }

    /**
     * gets the value of the denomination.
     * @return the value of the denomination.
     */
    public double getValue(){ return value; }

    /**
     * it converts the raw value into a bill (denomination)
     * @param rawDouble is the raw value of the bill
     * @return the converted denomination
     */
	public static Denomination of(double rawDouble) {
		Denomination[] allPossibleDenominations = Denomination.values();

		for (var possibleDenomination : allPossibleDenominations) {
			if (possibleDenomination.value == rawDouble) {
				return possibleDenomination;
			}
	
		}

		throw new IllegalArgumentException("Invalid denomination");
	}
}
