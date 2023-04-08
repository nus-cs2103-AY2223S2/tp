package seedu.dengue.model.range;

import java.util.Optional;

import seedu.dengue.model.person.Person;

/**
 * Represents the end of a given range
 */
public interface End<T> {
    public boolean isAfter(Person p);
    public Optional<T> get();
    public boolean isValidEnd(Start<T> start);
}
