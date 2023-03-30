package seedu.ultron.model.opening;

import java.time.LocalDate;
import java.util.Comparator;

public class DateComparator implements Comparator<Opening> {

    @Override
    public int compare(Opening o1, Opening o2) {

        if (o1.getKeydates().isEmpty() && o2.getKeydates().isEmpty()) {
            return 0;
        } else if (o1.getKeydates().isEmpty()) {
            return 1;
        } else if (o2.getKeydates().isEmpty()) {
            return -1;
        }

        LocalDate today = LocalDate.now();
        LocalDate o1Smallest = null;
        LocalDate o2Smallest = null;

        // find smallest date >= today for o1
        for (Keydate date : o1.getKeydates()) {
            LocalDate curr = LocalDate.parse(date.fullDate);
            if (curr.compareTo(today) >= 0) {
                if (o1Smallest == null || curr.compareTo(o1Smallest) < 0) {
                    o1Smallest = curr;
                }
            }
        }
        if (o1Smallest == null) {
            o1Smallest = LocalDate.MAX;
        }

        // find smallest date >= today for o2
        for (Keydate date : o2.getKeydates()) {
            LocalDate curr = LocalDate.parse(date.fullDate);
            if (curr.compareTo(today) >= 0) {
                if (o2Smallest == null || curr.compareTo(o2Smallest) < 0) {
                    o2Smallest = curr;
                }
            }
        }
        if (o2Smallest == null) {
            o2Smallest = LocalDate.MAX;
        }

        return o1Smallest.compareTo(o2Smallest);
    }
}
