package seedu.address.model.person;

import java.util.Comparator;

public class CompanyNameComparator implements Comparator<InternshipApplication> {
    @Override
    public int compare(InternshipApplication i, InternshipApplication j) {
        return i.getCompanyName().compareTo(j.getCompanyName());
    }
}
