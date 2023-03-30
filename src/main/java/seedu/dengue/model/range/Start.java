package seedu.dengue.model.range;

import seedu.dengue.model.person.Person;

/**
 * Represents the start of a given range
 */
public interface Start<T> {
    public boolean isBefore(Person p);
    public T get();
}
