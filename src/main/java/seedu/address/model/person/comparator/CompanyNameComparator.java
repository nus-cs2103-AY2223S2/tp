package seedu.address.model.person.comparator;

import java.util.Comparator;

import seedu.address.model.person.InternshipApplication;

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
}
