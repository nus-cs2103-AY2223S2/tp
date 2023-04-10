package seedu.dengue.model.range;

import java.util.Optional;

import seedu.dengue.model.person.Person;

/**
 * Represents the start of a given range
 */
public interface Start<T> {
    public boolean isBefore(Person p);
    public Optional<T> get();
    public boolean isValidStart(End<T> end);
}
