package seedu.address.model.application.comparator;

import java.util.Comparator;

import seedu.address.model.application.InternshipApplication;

/**
 * Encapsulates the comparator that compares JobTitle.
 */
public class JobTitleComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        if (i.getJobTitle() == null) {
            return 1;
        } else if (j.getJobTitle() == null) {
            return -1;
        }
        return i.getJobTitle().compareTo(j.getJobTitle());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitleComparator); // instanceof handles nulls
    }
}
