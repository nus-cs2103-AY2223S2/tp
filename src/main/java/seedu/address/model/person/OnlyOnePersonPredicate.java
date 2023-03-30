package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class OnlyOnePersonPredicate implements Predicate<Person> {
    private final String keywords;

    public OnlyOnePersonPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().fullName.equalsIgnoreCase(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OnlyOnePersonPredicate // instanceof handles nulls
                && keywords.equals(((OnlyOnePersonPredicate) other).keywords)); // state check
    }

}
