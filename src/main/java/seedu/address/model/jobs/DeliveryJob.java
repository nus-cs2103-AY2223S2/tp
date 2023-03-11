package seedu.address.model.jobs;

import java.util.UUID;

import seedu.address.model.person.Person;

/**
 * Represents delivery jobs entities.
 */
public class DeliveryJob {
    // Identity fields
    private final String jobId;

    // Delivery informations
    private Person recepient;
    private String deliverSlot; // TODO: Update data type when confirmed
    private double earning;

    /**
     * Constructs a job entity.
     *
     * @param recepient
     * @param deliverSlot
     * @param packages
     * @param earning
     */
    public DeliveryJob(Person recepient, String deliverSlot, double earning) {
        this(UUID.randomUUID().toString(), recepient, deliverSlot, earning);
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
    public DeliveryJob(String jobId, Person recepient, String deliverSlot, double earning) {
        this.jobId = jobId;
        this.recepient = recepient;
        this.deliverSlot = deliverSlot;
        this.earning = earning;
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

    public double getEarning() {
        return earning;
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
                + "earn: $%s\n";

        builder.append(
                String.format(outString,
                        jobId,
                        getRecepient(),
                        getDeliverSlot(),
                        getEarning()));

        return builder.toString();
    }
}
