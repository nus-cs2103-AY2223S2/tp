package seedu.address.model.person.comparator;

import java.util.Comparator;

import seedu.address.model.person.InternshipApplication;

/**
 * Encapsulates the comparator that compares InterviewDate.
 */
public class InterviewDateComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        if (i.getInterviewDate() == null) {
            return 1;
        } else if (j.getInterviewDate() == null) {
            return -1;
        }

        if (i.getInterviewDate().isBeforeInclusive(j.getInterviewDate())) {
            return i.getInterviewDate().equals(j.getInterviewDate()) ? 0 : -1;
        } else {
            return 1;
        }
    }
}
