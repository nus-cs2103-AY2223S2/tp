package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindContainsAnythingPredicate implements Predicate<Person> {
    private static Logger logger = Logger.getLogger("FindAnythingLogger");
    private final List<String> keywords;

    public FindContainsAnythingPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        logger.log(Level.INFO, person.toString());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAnythingIgnoreCase(person.toStringSpaceDelimited(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContainsAnythingPredicate // instanceof handles nulls
                && keywords.equals(((FindContainsAnythingPredicate) other).keywords)); // state check
    }

}
