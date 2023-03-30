package seedu.address.model.session;

import seedu.address.model.person.PayRate;

/**
 * A pair of a name and a pay rate indicating person's pay rate.
 */
public class NamePayRatePair {
    private String name;
    private PayRate payRate;
    /**
     * Constructs a {@code NamePayRatePair} object with the given name and presence status.
     * @param name The name of the person.
     * @param payRate The pay rate of the person.
     */

    public NamePayRatePair(String name, PayRate payRate) {
        this.name = name;
        this.payRate = payRate;
    }

    /**
     * Returns the name of the person in this pair.
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the pay rate of the person.
     * @return PayRate of person.
     */
    public PayRate getPayRate() {
        return payRate;
    }
    @Override
    public String toString() {
        return name + ": " + payRate.toString();
    }
}
