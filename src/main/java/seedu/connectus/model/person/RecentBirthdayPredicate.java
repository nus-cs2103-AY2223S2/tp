package seedu.connectus.model.person;

import java.util.Optional;
import java.util.function.Predicate;

public class RecentBirthdayPredicate implements Predicate<Person> {
    public RecentBirthdayPredicate() {
    }

    @Override
    public boolean test(Person person) {
        Optional<Birthday> bd = person.getBirthday();

        if (bd.isEmpty()) {
            return false;
        }

        Birthday birthday = bd.get();
        return birthday.isRecent();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecentBirthdayPredicate); // instanceof handles nulls
    }
}
