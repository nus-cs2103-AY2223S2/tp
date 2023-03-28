package seedu.ultron.model.opening;

import seedu.ultron.logic.parser.ParserUtil;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Predicate;

public class OpeningsBeforeDaysPredicate implements Predicate<Opening> {

    int days;

    public OpeningsBeforeDaysPredicate(int days) {
        this.days = days;
    }

    @Override
    public boolean test(Opening opening) {
        boolean hasEvent = false;
        LocalDate today = LocalDate.now();
        LocalDate remindLimit = today.plusDays(days);
        Set<Date> allDates = opening.getDates();
        for (Date d : allDates) {
            LocalDate eventDate = ParserUtil.getTime(d.fullDate);
            if (!eventDate.isAfter(remindLimit) && !eventDate.isBefore(today)) {
                hasEvent = true;
            }
        }
        return hasEvent;
    }
}
