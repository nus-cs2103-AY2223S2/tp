package seedu.address.commons.util;
import seedu.address.model.module.Module;
import java.util.Comparator;

public class ComparatorUtil {
    public static final Comparator<Module> SORT_BY_TIMESLOT_COMPARATOR =
            (Module1, Module2) -> Module1.compareTimeSlot(Module2);
    public static final Comparator<Module> SORT_BY_DEADLINE_COMPARATOR =
            (Module1, Module2) -> Module1.compareDeadline(Module2);

    public static Comparator<Module> getComparator(String keyword) {
        if (keyword.equals("timeslot")) {
            return SORT_BY_TIMESLOT_COMPARATOR;
        } else {
            return SORT_BY_DEADLINE_COMPARATOR;
        }
    }
}
