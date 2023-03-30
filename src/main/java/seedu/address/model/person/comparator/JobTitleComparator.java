package seedu.address.model.person.comparator;

import java.util.Comparator;

import seedu.address.model.person.InternshipApplication;

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
}
