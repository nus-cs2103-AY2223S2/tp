package seedu.dengue.model.range;

import seedu.dengue.model.person.Person;

public interface End<T> {
    public boolean isAfter(Person p);
}
