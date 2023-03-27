package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalDateTime;

public class Cost {

    public double rate;

    public final LocalDateTime timeStamp;

    public double flatCost;

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

    public double calculate() {
        double amount = Duration.between(timeStamp, LocalDateTime.now()).getSeconds()  * rate + flatCost;
        return Math.max(amount, 0.00);
    }

    public String toString() {
        double amount = calculate();
        return "Cost: $" + Math.round(amount * 100.00) /100.00;
    }
}
