package seedu.address.model.person;

import java.util.function.Predicate;

public class FullNamePredicate implements Predicate<Person> {
    private final String fullName;

    public FullNamePredicate(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().fullName.equals(this.fullName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FullNamePredicate
                && fullName.equals(((FullNamePredicate) other).fullName));
    }
}
