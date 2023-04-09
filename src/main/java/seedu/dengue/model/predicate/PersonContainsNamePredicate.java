package seedu.dengue.model.predicate;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;

/**
 * Represents the predicate which tests for whether the person in the persons list has a valid substring of
 * the name given in the user input.
 */
public class PersonContainsNamePredicate extends PredicateUtil<Person> {

    private final Optional<Name> name;

    /**
     * Constructs a predicate used to test whether the name is a part of a person in the case list.
     * @param name optional name of the predicate which is used to test with a person.
     */
    public PersonContainsNamePredicate(Optional<Name> name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public boolean test(Person person) {
        if (name.isPresent()) {
            Predicate<String> containsName =
                    keyword -> StringUtil.containsSubstringWithLengthMoreThanOneIgnoreCase(
                            person.getName().fullName, keyword);
            return containsName.test(name.get().fullName);
        } else {
            return true;
        }
    }
}
