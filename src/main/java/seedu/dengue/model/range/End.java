package seedu.dengue.model.range;

import seedu.dengue.model.person.Person;

/**
 * Represents the end of a given range
 */
public interface End<T> {
    public boolean isAfter(Person p);
    public T get();
}
