package seedu.address.model.deliveryjobs;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Person;


/**
 * Represents a Delivery job in the Delivery jobs book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * @deprecated Merging with jobs.DeliveryJob
 */
@Deprecated
public class DeliveryJob {

    private final int id;
    private final Person customer;
    private final LocalDateTime deliveryTime;
    private final double earning;
    private final boolean isDelivered;
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy EEE HHmm a");


    /**
     * Every field must be present and not null.
     */
    DeliveryJob(int id, Person customer, LocalDateTime deliveryTime, double earning, boolean isDelivered) {
        requireAllNonNull(id, customer, deliveryTime, earning, isDelivered);
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

    /**
     * Returns details of the delivery job.
     *
     * @return delivery job details.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getID())
                .append("; Customer: ")
                .append(this.customer.getName())
                .append("; Delivery time: ")
                .append(getDeliveryTime())
                .append("; Pay: ")
                .append(getEarning())
                .append("; Delivery status: ")
                .append(this.getIsDelivered());

        return builder.toString();
    }
}
