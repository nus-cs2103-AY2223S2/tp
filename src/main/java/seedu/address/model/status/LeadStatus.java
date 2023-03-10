package seedu.address.model.status;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.status.LeadStatusName.QUALIFIED;
import static seedu.address.model.status.LeadStatusName.UNCONTACTED;
import static seedu.address.model.status.LeadStatusName.UNQUALIFIED;
import static seedu.address.model.status.LeadStatusName.WORKING;


/**
 * Represents the lead status of a contact.
 * A lead status is immutable, and has restricted values.
 */
public class LeadStatus {
    public static final String MESSAGE_CONSTRAINTS = "A lead status can only be "
            + "one of the following 4 categories: \n"
            + UNCONTACTED + "\n"
            + WORKING + "\n"
            + QUALIFIED + "\n"
            + UNQUALIFIED;

    public final String statusName;
    //TODO add in LocalDateTime to record timestamp of status creation

    public LeadStatus(String statusName) {
        requireNonNull(statusName);
        checkArgument(LeadStatusName.isValidLeadStatus(statusName),
                MESSAGE_CONSTRAINTS);
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LeadStatus // instanceof handles nulls
                && statusName.equals(((LeadStatus) other).statusName)); // state check
    }
}
