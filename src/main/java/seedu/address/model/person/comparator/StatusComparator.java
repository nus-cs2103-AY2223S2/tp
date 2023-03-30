package seedu.address.model.person.comparator;

import java.util.Comparator;

import seedu.address.model.person.InternshipApplication;

/**
 * Encapsulates the comparator that compares InternshipStatus.
 */
public class StatusComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        return i.getStatus().compareTo(j.getStatus());
    }
}
