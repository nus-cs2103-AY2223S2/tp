package seedu.address.model.application.comparator;

import java.util.Comparator;

import seedu.address.model.application.InternshipApplication;

/**
 * Encapsulates the comparator that compares InternshipStatus.
 */
public class StatusComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        return i.getStatus().compareTo(j.getStatus());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusComparator); // instanceof handles nulls
    }
}
