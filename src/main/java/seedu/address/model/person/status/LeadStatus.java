package seedu.address.model.person.status;

import seedu.address.model.Status;

import java.time.Duration;
import java.time.Instant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.status.LeadStatusName.QUALIFIED;
import static seedu.address.model.person.status.LeadStatusName.UNCONTACTED;
import static seedu.address.model.person.status.LeadStatusName.UNQUALIFIED;
import static seedu.address.model.person.status.LeadStatusName.WORKING;
import static seedu.address.model.person.status.LeadStatusName.isValidLeadStatusName;


/**
 * Represents the lead status of a contact.
 * A lead status is immutable, and has restricted values.
 */
public class LeadStatus extends Status {
    public static final String MESSAGE_CONSTRAINTS = "A lead status can only be "
            + "one of the following 4 categories: \n"
            + UNCONTACTED + "\n"
            + WORKING + "\n"
            + QUALIFIED + "\n"
            + UNQUALIFIED;

    private final LeadStatusName statusName;
    private final Instant timestamp;

    /**
     * Creates a LeadStatus given a valid String key for a LeadStatusName.
     */
    public LeadStatus(String statusName) {
        requireNonNull(statusName);
        checkArgument(isValidLeadStatusName(statusName), MESSAGE_CONSTRAINTS);
        this.statusName = LeadStatusName.get(statusName);
        this.timestamp = Instant.now();
    }

    public LeadStatus(String statusName, String timestampInIso) {
        requireAllNonNull(statusName, timestampInIso);
        checkArgument(isValidLeadStatusName(statusName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTimestamp(timestampInIso), MESSAGE_CONSTRAINTS);
        this.statusName = LeadStatusName.get(statusName);
        this.timestamp = Instant.parse(timestampInIso);
    }

    public LeadStatusName getStatusName() {
        return statusName;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public Duration getDurationSinceLastUpdate() {
        return Duration.between(timestamp, Instant.now());
    }

    @Override
    public String getInstantInISO() {
        return this.timestamp.toString();
    }

    public static boolean isValidLeadStatus(String name, String timestamp) {
        return LeadStatusName.isValidLeadStatusName(name)
                && Status.isValidTimestamp(timestamp);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LeadStatus // instanceof handles nulls
                && statusName.equals(((LeadStatus) other).statusName)); // state check
    }

    @Override
    public String toString() {
        return statusName.toString();
    }
}
