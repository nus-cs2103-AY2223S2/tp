package seedu.ultron.model.opening;

import java.time.LocalDate;
import java.util.Comparator;

public class OpeningByDateComparator implements Comparator<Opening> {

    @Override
    public int compare(Opening o1, Opening o2) {

        if (o1.getDates().isEmpty() && o2.getDates().isEmpty()) {
            return 0;
        } else if (o1.getDates().isEmpty()) {
            return 1;
        } else if (o2.getDates().isEmpty()) {
            return -1;
        }

        Date o1Smallest = null;
        Date o2Smallest = null;

        // find smallest date >= today for o1
        for (Date date : o1.getDates()) {
            // if date is not past date
            if (!date.isPastDate()) {
                // date is the smallest date
                o1Smallest = date;
                break;
            }

        }

        // find smallest date >= today for o2
        for (Date date : o2.getDates()) {
            // if date is not past date
            if (!date.isPastDate()) {
                // date is the smallest date
                o2Smallest = date;
                break;
            }
        }

        // if both openings have no dates >= today
        if (o1Smallest == null && o2Smallest == null) {
            return o1.getDates().get(o1.getDates().size() - 1).compareTo(o2.getDates().get(o2.getDates().size() - 1));
        } else if (o1Smallest == null) {
            return 1;
        } else if (o2Smallest == null) {
            return -1;
        }

        return o1Smallest.compareTo(o2Smallest);
    }
}
