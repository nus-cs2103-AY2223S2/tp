package seedu.address.model.application;

import java.util.Comparator;

/**
 * Compares two applications based on alphabetical order of company name.
 */
public class AlphabeticalComparator implements Comparator<Application> {

    @Override
    public int compare(Application a, Application b) {
        if (a.getCompanyName().toString().compareToIgnoreCase(b.getCompanyName().toString()) > 0) {
            return 1;
        } else if (a.getCompanyName().toString().compareToIgnoreCase(b.getCompanyName().toString()) < 0) {
            return -1;
        } else {
            // use role as tiebreaker
            return Integer.compare(a.getRole().toString().compareToIgnoreCase(b.getRole().toString()), 0);
        }
    }
}
