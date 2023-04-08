package seedu.ultron.model.opening;

import java.util.Comparator;

/**
 * Represents a {@code Comparator} to compare dates of 2 {@code Openings}.
 */
public class OpeningByDateComparator implements Comparator<Opening> {

    @Override
    public int compare(Opening o1, Opening o2) {

        if (o1.getKeydates().isEmpty() && o2.getKeydates().isEmpty()) {
            return 0;
        } else if (o1.getKeydates().isEmpty()) {
            return 1;
        } else if (o2.getKeydates().isEmpty()) {
            return -1;
        }

        Keydate o1Smallest = null;
        Keydate o2Smallest = null;

        // find smallest date >= today for o1
        for (Keydate date : o1.getKeydates()) {
            // if date is not past date
            if (!date.isPastKeydate()) {
                // date is the smallest date
                o1Smallest = date;
                break;
            }

        }

        // find smallest date >= today for o2
        for (Keydate date : o2.getKeydates()) {
            // if date is not past date
            if (!date.isPastKeydate()) {
                // date is the smallest date
                o2Smallest = date;
                break;
            }
        }

        // if both openings have no dates >= today
        if (o1Smallest == null && o2Smallest == null) {
            return o1.getKeydates().get(o1.getKeydates().size() - 1).compareTo(o2.getKeydates()
                    .get(o2.getKeydates().size() - 1));
        } else if (o1Smallest == null) {
            return 1;
        } else if (o2Smallest == null) {
            return -1;
        }

        return o1Smallest.compareTo(o2Smallest);
    }
}
