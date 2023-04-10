package seedu.connectus.model.person;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Birthday} is upcoming (in the next two
 * months).
 */
public class RecentBirthdayPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        Optional<Birthday> bd = person.getBirthday();

        if (bd.isEmpty()) {
            return false;
        }

        Birthday birthday = bd.get();
        return birthday.isUpcoming();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecentBirthdayPredicate); // instanceof handles nulls
    }
}
