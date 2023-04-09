package seedu.address.model.application.comparator;

import java.util.Comparator;

import seedu.address.model.application.BetweenDatePredicate;
import seedu.address.model.application.InternshipApplication;

/**
 * Encapsulates the comparator that compares CompanyName.
 */
public class CompanyNameComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        if (i.getCompanyName() == null) {
            return 1;
        } else if (j.getCompanyName() == null) {
            return -1;
        }
        return i.getCompanyName().compareTo(j.getCompanyName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyNameComparator); // instanceof handles nulls
    }
}
