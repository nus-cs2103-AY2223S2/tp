package seedu.quickcontacts.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.quickcontacts.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code Name} matches any of the keywords given.
 */
public class MeetingContainsNamesPredicate implements Predicate<Meeting> {

    private final List<String> names;

    public MeetingContainsNamesPredicate(List<String> names) {
        this.names = names;
    }
    public MeetingContainsNamesPredicate() {
        this.names = null;
    }

    /**
     * @param meeting the input argument to be tested
     * @return True if the meeting contains any person whose name contain any of the keywords
     */
    @Override
    public boolean test(Meeting meeting) {
        if (this.names == null) {
            return true;
        }
        return names.stream().anyMatch(name -> meeting.getAttendees().stream().anyMatch(person ->
            StringUtil.containsWordIgnoreCase(person.getName().fullName, name)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } // short circuit if same object
        if (!(other instanceof MeetingContainsNamesPredicate)) {
            return false;
        } // instanceof handles nulls
        if (names == null) {
            return ((MeetingContainsNamesPredicate) other).names == null;
        }
        return names.equals(((MeetingContainsNamesPredicate) other).names); // state check
    }

    public List<String> getPersonNames() {
        return names;
    }
}
