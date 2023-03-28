package seedu.address.model.person.comparator;

import java.util.Comparator;

import seedu.address.model.person.InternshipApplication;

public class CompanyNameComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        return i.getCompanyName().compareTo(j.getCompanyName());
    }
}
