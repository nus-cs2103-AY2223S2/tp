package seedu.fitbook.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Appointment} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public AppointmentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        boolean testClient = false;
        for (Appointment appointment : client.getAppointments()) {
            if (keywords.stream().anyMatch(keyword -> StringUtil
                    .containsWordIgnoreCase(appointment.appointmentTime, keyword))) {
                testClient = true;
                break;
            }
        }
        return testClient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
