package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MatchNamePredicate implements Predicate<Person> {
    private final List<Name> names;

    public MatchNamePredicate(List<Name> names) {
        this.names = names;
    }

    @Override
    public boolean test(Person person) {
        return names.stream()
                .anyMatch(name -> person.getName().equals(name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchNamePredicate // instanceof handles nulls
                && names.equals(((MatchNamePredicate) other).names)); // state check
    }
}
