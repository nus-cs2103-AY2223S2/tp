package seedu.address;

import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeliveryJob {

    private final int id;
    private final Person customer;
    private final LocalDateTime deliveryTime;
    private final double earning;
    private final boolean isDelivered;
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy EEE HHmm a");


    DeliveryJob(int id, Person customer, LocalDateTime deliveryTime, double earning, boolean isDelivered) {
        this.id = id;
        this.customer = customer;
        this.deliveryTime = deliveryTime;
        this.earning = earning;
        this.isDelivered = isDelivered;
    }

    public int getID() {
        return this.id;
    }

    public Person getCustomer() {
        return this.customer;
    }

    public String getDeliveryTime() {
        return this.deliveryTime.format(dateTimeFormatter);
    }

    public double getEarning() {
        return this.earning;
    }

    public boolean getIsDelivered() {
        return this.isDelivered;
    }
}
