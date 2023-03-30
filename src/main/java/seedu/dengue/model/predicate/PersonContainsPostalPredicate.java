package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;

/**
 * Represents the predicate which tests for whether the person in the persons list has a valid subpostal based
 * on the subpostal made using the user input.
 */
public class PersonContainsPostalPredicate extends PredicateUtil<Person> {

    private final Optional<SubPostal> subPostal;

    public PersonContainsPostalPredicate(Optional<SubPostal> subPostal) {
        this.subPostal = subPostal;
    }

    @Override
    public boolean test(Person person) {
        if (subPostal.isPresent()) {
            Predicate<String> containsPostal =
                    keyword -> StringUtil.containsSubstringIgnoreCase(person.getPostal().value,
                            keyword);
            return containsPostal.test(subPostal.get().value);
        } else {
            return true;
        }
    }
}
