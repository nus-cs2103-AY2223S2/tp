package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;

public class PersonContainsPostalPredicate implements Predicate<Person> {

    private final Optional<SubPostal> subPostal;

    public PersonContainsPostalPredicate(Optional<SubPostal> subPostal) {
        this.subPostal = subPostal;
    }

    @Override
    public boolean test(Person person) {
        if (subPostal.isPresent()) {
            Predicate<String> containsPostal =
                    keyword -> StringUtil.containsSubstringIgnoreCase(person.getPostal().value, keyword);
            return containsPostal.test(subPostal.get().value);
        } else {
            return true;
        }
    }
}
