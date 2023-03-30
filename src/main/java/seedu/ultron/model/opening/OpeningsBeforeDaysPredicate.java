package seedu.ultron.model.opening;


import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import seedu.ultron.logic.parser.ParserUtil;

public class OpeningsBeforeDaysPredicate implements Predicate<Opening> {

    private int days;

    public OpeningsBeforeDaysPredicate(int days) {
        this.days = days;
    }

    @Override
    public boolean test(Opening opening) {
        boolean hasEvent = false;
        LocalDate today = LocalDate.now();
        LocalDate remindLimit = today.plusDays(days);
        List<Keydate> allKeydates = opening.getKeydates();
        for (Keydate d : allKeydates) {
            LocalDate eventDate = ParserUtil.getTime(d.fullDate);
            if (!eventDate.isAfter(remindLimit) && !eventDate.isBefore(today)) {
                hasEvent = true;
            }
        }
        return hasEvent;
    }
}
