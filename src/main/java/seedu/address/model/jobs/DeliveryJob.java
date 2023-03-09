package seedu.address.model.jobs;

import java.util.List;
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
    private List<DeliveryPackage> packages;
    private double earning;

    /**
     * Constructs a job entity.
     *
     * @param recepient
     * @param deliverSlot
     * @param packages
     * @param earning
     */
    public DeliveryJob(Person recepient, String deliverSlot, List<DeliveryPackage> packages, double earning) {
        this(UUID.randomUUID().toString(), recepient, deliverSlot, packages, earning);
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
    public DeliveryJob(String jobId, Person recepient, String deliverSlot, List<DeliveryPackage> packages,
            double earning) {
        this.jobId = jobId;
        this.recepient = recepient;
        this.deliverSlot = deliverSlot;
        this.packages = packages;
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

    public List<DeliveryPackage> getPackages() {
        return packages;
    }

    public double getEarning() {
        return earning;
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
