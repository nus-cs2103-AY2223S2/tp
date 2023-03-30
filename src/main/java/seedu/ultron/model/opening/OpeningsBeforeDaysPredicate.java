package seedu.ultron.model.opening;


import java.util.List;
import java.util.function.Predicate;


public class OpeningsBeforeDaysPredicate implements Predicate<Opening> {

    private int days;

    public OpeningsBeforeDaysPredicate(int days) {
        this.days = days;
    }

    @Override
    public boolean test(Opening opening) {
        List<Keydate> allDates = opening.getKeydates();
        for (Keydate d : allDates) {
            if (d.isWithinDays(days)) {
                return true;
            }
        }
        return false;
    }
}
