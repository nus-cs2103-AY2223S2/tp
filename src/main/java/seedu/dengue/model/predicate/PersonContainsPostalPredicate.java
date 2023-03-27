package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;

public class PersonContainsPostalPredicate implements Predicate<Person> {

    private final Optional<Postal> postal;

    public PersonContainsPostalPredicate(Optional<Postal> postal) {
        this.postal = postal;
    }

    @Override
    public boolean test(Person person) {
        if (postal.isPresent()) {
            Predicate<String> containsPostal =
                    keyword -> StringUtil.containsSubstringIgnoreCase(person.getPostal().value, keyword);
            return containsPostal.test(postal.get().value);
        } else {
            return true;
        }
    }
}
