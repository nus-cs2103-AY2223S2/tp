package seedu.fitbook.model.client;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.tag.Tag;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class FindContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public FindContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        boolean testClient = false;
        Iterator<Appointment> appIterator = client.getAppointments().iterator();
        Iterator<Tag> tagIterator = client.getTags().iterator();
        if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getName().fullName, keyword))) {
            testClient = true;
        } else if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getPhone().value, keyword))) {
            testClient = true;
        } else if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getEmail().value, keyword))) {
            testClient = true;
        } else if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getAddress().value, keyword))) {
            testClient = true;
        } else if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getCalorie().value, keyword))) {
            testClient = true;
        } else if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getWeight().value, keyword))) {
            testClient = true;
        } else if (keywords.stream().anyMatch(keyword -> StringUtil.
                containsWordIgnoreCase(client.getGender().value, keyword))) {
            testClient = true;
        } else {
            while (appIterator.hasNext()) {
                Appointment appointment = appIterator.next();
                if (keywords.stream().anyMatch(keyword -> StringUtil.
                        containsWordIgnoreCase(appointment.appointmentTime, keyword))) {
                    testClient = true;
                    break;
                }
            }

            while (tagIterator.hasNext()) {
                Tag tag = tagIterator.next();
                if (keywords.stream().anyMatch(keyword -> StringUtil.
                        containsWordIgnoreCase(tag.tagName, keyword))) {
                    testClient = true;
                    break;
                }
            }
        }
        return testClient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FindContainsKeywordsPredicate) other).keywords)); // state check
    }

}
