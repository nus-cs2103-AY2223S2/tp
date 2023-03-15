package seedu.address.model.jobs;

import java.time.LocalDate;
import java.util.UUID;

import seedu.address.model.person.Person;

/**
 * Represents delivery jobs entities.
 */
public class DeliveryJob {
    // Identity fields
    private final String jobId;

    // Delivery informations
    private final Person recepient;
    private final String deliverSlot; // TODO: Update data type when confirmed
    private final Earning earning;
    private final boolean isDelivered;

    /**
     * Constructs a job entity.
     *
     * @param recepient
     * @param deliverSlot
     * @param packages
     * @param earning
     */
    public DeliveryJob(Person recepient, String deliverSlot, String earning) {
        this(UUID.randomUUID().toString(), recepient, deliverSlot, earning, false);
    }

    /**
     * DeliveryJob
     *
     * @param jobId
     * @param recepient
     * @param deliverSlot
     * @param packages
     * @param earning
     */
    public DeliveryJob(String jobId, Person recepient, String deliverSlot, String earning, boolean isDelivered) {
        this.jobId = jobId;
        this.recepient = recepient;
        this.deliverSlot = deliverSlot;
        this.earning = new Earning(earning);
        this.isDelivered = isDelivered;
    }

    public String getJobId() {
        return jobId;
    }

    public Person getRecepient() {
        return recepient;
    }

    public String getDeliverSlot() {
        return deliverSlot;
    }

    public Earning getEarning() {
        return earning;
    }

    public boolean getDeliveredStatus() {
        return isDelivered;
    }
    public LocalDate getDeliverDate() {
        return LocalDate.now();
    }


    /**
     * isSameDeliveryJob.
     *
     * @param otherJob
     * @return
     */
    public boolean isSameDeliveryJob(DeliveryJob otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null && otherJob.getJobId().equals(getJobId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        String outString = "Job [%s]\n"
                + "receipent: %s\n"
                + "slot: %s\n"
                + "earn: $%s\n"
                + "status: $%s\n";

        builder.append(
                String.format(outString,
                        jobId,
                        getRecepient(),
                        getDeliverSlot(),
                        getEarning(),
                        getDeliveredStatus())
        );

        return builder.toString();
    }
}
