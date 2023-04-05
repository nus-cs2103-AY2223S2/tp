package seedu.address.model.jobs;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents delivery jobs entities.
 */
public class DeliveryJob {
    // Identity fields
    private final String jobId;

    // Delivery informations
    private final String recipient;
    private final String sender; // aka customer
    private final Optional<DeliveryDate> deliveryDate;
    private final Optional<DeliverySlot> deliverySlot;
    private final Optional<Earning> earning;
    private final Boolean isDelivered;
    private final String description;

    /**
     * Constructs a job entity.
     *
     * @param recipient
     * @param sender
     * @param earning
     */
    public DeliveryJob(String recipient, String sender, String earning, String description) {
        this(genJobId(recipient, sender), recipient, sender, Optional.empty(), Optional.empty(),
                Optional.of(new Earning(earning)), false, description);
    }

    /**
     * Constructs a job entity.
     *
     * @param recipient
     * @param sender
     * @param deliveryDate
     * @param deliverySlot
     * @param earning
     */
    public DeliveryJob(String recipient, String sender, Optional<DeliveryDate> deliveryDate,
                       Optional<DeliverySlot> deliverySlot, Optional<Earning> earning,
                       String description) {
        this(genJobId(recipient, sender), recipient, sender, deliveryDate,
                deliverySlot, earning, false, description);
    }

    /**
     * Constructs a job entity.
     *
     * @param recipient
     * @param sender
     * @param deliveryDate
     * @param deliverySlot
     * @param earning
     */
    public DeliveryJob(String recipient, String sender, String deliveryDate, String deliverySlot, String earning,
            String description) {
        this(genJobId(recipient, sender), recipient, sender, Optional.of(new DeliveryDate(deliveryDate)),
                Optional.of(new DeliverySlot(deliverySlot)), Optional.of(new Earning(earning)), false, description);
    }

    /**
     * DeliveryJob
     *
     * @param jobId
     * @param recipient
     * @param deliverySlot
     * @param sender
     * @param earning
     * @param isDelivered
     */
    public DeliveryJob(String jobId, String recipient, String sender, Optional<DeliveryDate> deliveryDate,
            Optional<DeliverySlot> deliverySlot,
            Optional<Earning> earning,
            Boolean isDelivered, String description) {
        this(true, jobId, recipient, sender, deliveryDate,
                deliverySlot, earning, isDelivered,
                description);
    }

    /**
     * A special constructor to bypass null check.
     *
     * @param jobId
     * @param recipient
     * @param deliverySlot
     * @param sender
     * @param earning
     * @param isDelivered
     */
    public DeliveryJob(boolean checkNull, String jobId, String recipient, String sender,
            Optional<DeliveryDate> deliveryDate,
            Optional<DeliverySlot> deliverySlot,
            Optional<Earning> earning,
            Boolean isDelivered, String description) {
        if (checkNull) {
            requireAllNonNull(jobId, recipient, sender, deliveryDate, deliverySlot, earning, isDelivered, description);
        }
        this.jobId = jobId;
        this.recipient = recipient;
        this.sender = sender;
        this.deliveryDate = deliveryDate;
        this.deliverySlot = deliverySlot;
        this.earning = earning;
        this.isDelivered = isDelivered;
        this.description = description;
    }

    private static String genJobId(String recipient, String sender) {
        requireAllNonNull(recipient, sender);
        return recipient.substring(0, 2)
                .concat(sender.substring(0, 2))
                .concat(UUID.randomUUID().toString().substring(0, 6))
                .toUpperCase();
    }

    /**
     * Returns job ID
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Returns recipient ID
     */
    public String getRecipientId() {
        return recipient;
    }

    /**
     * Returns sender ID
     */
    public String getSenderId() {
        return sender;
    }

    /**
     * Returns delivery date
     */
    public Optional<DeliveryDate> getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Returns delivery slot
     */
    public Optional<DeliverySlot> getDeliverySlot() {
        return deliverySlot;
    }

    /**
     * Returns delivery date in LocalDate
     */
    public LocalDate getDate() {
        return deliveryDate.get().getDate();
    }

    /**
     * Returns delivery slot in Integer
     */
    public int getSlot() {
        return deliverySlot.get().getSlot();
    }

    /**
     * Returns delivery earning
     */
    public Optional<Earning> getEarning() {
        return earning;
    }

    /**
     * Returns delivered status
     */
    public Boolean getDeliveredStatus() {
        return isDelivered;
    }

    /**
     * Returns delivery description in proper String format
     */
    public String getDescription() {
        if (description == null) {
            return "";
        }
        return description;
    }

    /**
     * Checks if job has delivery date or slot
     * @return
     */
    public boolean hasDateOrSlot() {
        return hasDate() || hasSlot();
    }

    /**
     * Checks if job has delivery date
     * @return boolean
     */
    public boolean hasDate() {
        return getDeliveryDate().isPresent();
    }

    /**
     * Checks if job has delivery slot
     * @return boolean
     */
    public boolean hasSlot() {
        return getDeliverySlot().isPresent();
    }

    /**
     * Checks if job has invalid delivery slot
     * @return boolean
     */
    public boolean hasInvalidSlot() {
        return getDeliverySlot().isPresent() && (!getDeliverySlot().get().isValidRange());
    }

    /**
     * Checks if job has earning
     * @return boolean
     */
    public boolean hasEarning() {
        return getEarning().isPresent();
    }

    /**
     * Checks if job has delivery date and slot.
     *
     * @return boolean
     */
    public boolean isScheduled() {
        return getDeliveryDate().isPresent() && getDeliverySlot().isPresent()
                && getDeliverySlot().get().isPositive();
    }

    /**
     * Checks if job has valid delivery date and slot
     * @return
     */
    public boolean isValidScheduled() {
        return isScheduled() && deliverySlot.get().isValidRange();
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
        builder.append("[Job detail]\n");
        if (getJobId() != null) {
            builder.append("Job Id: " + getJobId() + "\n");
        }

        if (getSenderId() != null) {
            builder.append("Sender Id: " + getSenderId() + "\n");
        }

        if (getRecipientId() != null) {
            builder.append("Recipient Id: " + getRecipientId() + "\n");
        }

        if (getDeliveryDate().isPresent()) {
            builder.append("Delivery Date: " + getDeliveryDate().get() + "\n");
        }

        if (getDeliverySlot().isPresent()) {
            builder.append("Delivery Slot: " + getDeliverySlot().get() + "\n");
        }

        if (getEarning().isPresent()) {
            builder.append("Earning: " + getEarning().get() + "\n");
        }

        if (getDeliveredStatus() != null) {
            builder.append("Status: " + (getDeliveredStatus() ? "Delivered" : "Pending") + "\n");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeliveryJob)) {
            return false;
        }

        DeliveryJob otherJob = (DeliveryJob) other;
        return otherJob.getRecipientId().equals(getRecipientId())
                && otherJob.getSenderId().equals(getSenderId())
                && otherJob.getEarning().equals(getEarning());
    }

    /**
     * Buider class for building DeliveryJob.
     */
    public static class Builder {
        private String jobId;
        private String recipient;
        private String sender; // aka customer
        private Optional<DeliveryDate> deliveryDate = Optional.empty();
        private Optional<DeliverySlot> deliverySlot = Optional.empty();
        private Optional<Earning> earning = Optional.empty();
        private Boolean isDelivered;
        private String description;

        /**
         * Copys from an existing job.
         *
         * @param job
         * @return
         */
        public Builder copy(DeliveryJob job) {
            this.jobId = job.getJobId();
            this.recipient = job.getRecipientId();
            this.sender = job.getSenderId();
            this.deliveryDate = job.getDeliveryDate();
            this.deliverySlot = job.getDeliverySlot();
            this.earning = job.getEarning();
            this.isDelivered = job.getDeliveredStatus();
            this.description = job.getDescription();
            return this;
        }

        /**
         * Sets jobid.
         *
         * @param id
         * @return
         */
        public Builder setJobId(String id) {
            this.jobId = id;
            return this;
        }

        /**
         * Sets recipient.
         *
         * @param id
         * @return
         */
        public Builder setRecipient(String id) {
            this.recipient = id;
            return this;
        }

        /**
         * Sets sender.
         *
         * @param id
         * @return
         */
        public Builder setSender(String id) {
            this.sender = id;
            return this;
        }

        /**
         * Sets deliveryDate.
         *
         * @param date
         * @return
         */
        public Builder setDeliveryDate(String date) {
            this.deliveryDate = Optional.of(new DeliveryDate(date));
            return this;
        }

        /**
         * Sets deliverySlot.
         *
         * @param slot
         * @return
         */
        public Builder setDeliverySlot(String slot) {
            this.deliverySlot = Optional.of(new DeliverySlot(slot));
            return this;
        }

        /**
         * Clears deliverySlot.
         *
         * @return
         */
        public Builder clearDeliverySlot() {
            this.deliverySlot = Optional.of(DeliverySlot.placeholder());
            return this;
        }

        /**
         * Clears deliveryDate.
         *
         * @return
         */
        public Builder clearDeliveryDate() {
            this.deliveryDate = Optional.of(DeliveryDate.placeholder());
            return this;
        }

        /**
         * Sets earning.
         *
         * @param earn
         * @return
         */
        public Builder setEarning(String earn) {
            this.earning = Optional.of(new Earning(earn));
            return this;
        }

        /**
         * Sets isDelivered.
         *
         * @param isDelivered
         * @return
         */
        public Builder setDeliveredStatus(boolean isDelivered) {
            this.isDelivered = isDelivered;
            return this;
        }

        /**
         * Sets description.
         *
         * @param description
         * @return
         */
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Builds DeliveryJob.
         */
        public DeliveryJob build() {
            return new DeliveryJob(jobId, recipient, sender,
                    deliveryDate,
                    deliverySlot, earning, isDelivered, description);
        }

        /**
         * Builds DeliveryJob.
         */
        public DeliveryJob buildNullable() {
            return new DeliveryJob(false, jobId, recipient, sender,
                    deliveryDate,
                    deliverySlot, earning, isDelivered, description);
        }
    }
}
