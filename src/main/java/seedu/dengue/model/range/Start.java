package seedu.dengue.model.range;

import seedu.dengue.model.person.Person;

public interface Start<T> {
    public boolean isBefore(Person p);
}
