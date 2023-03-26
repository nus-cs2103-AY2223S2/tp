package seedu.dengue.model.predicate;

import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.person.Person;

public class PersonContainsPostalPredicate implements Predicate<Person> {

    private final Postal postal;

    public PersonContainsPostalPredicate(Postal postal) {
        this.postal = postal;
    }

    @Override
    public boolean test(Person person) {
        Predicate<String> containsPostal =
                keyword -> StringUtil.containsSubstringIgnoreCase(person.getPostal().value, keyword);

        return containsPostal.test(postal.value);
    }
}
