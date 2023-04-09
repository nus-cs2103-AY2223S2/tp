package seedu.address.model.pet;


import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Cost object with fields (LocalDateTime)
 * Represents the cost calculated from a set rate
 */
public class Cost {

    private double rate;

    private final LocalDateTime timeStamp;

    private double flatCost;

    /**
     * Constructs a {@code Cost}
     * @param timeStamp LocalDateTime
     */
    public Cost(LocalDateTime timeStamp) {
        requireNonNull(timeStamp);

        this.timeStamp = timeStamp;
        this.rate = 0.10;
        this.flatCost = 0.00;
    }


    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setFlatCost(double flatCost) {
        this.flatCost = flatCost;
    }

    /**
     * Returns the integer cost amount calculated from the rate and flatCost at the invoked time
     */
    public double calculate() {
        double amount = Duration.between(timeStamp, LocalDateTime.now()).getSeconds() * rate + flatCost;
        return Math.max(amount, 0.00);
    }

    /**
     * toString method
     * @return String with the cost amount rounded to 2 decimal places
     */
    public String toString() {
        double amount = calculate();
        return "$" + Math.round(amount * 100.00) / 100.00;
    }
}
