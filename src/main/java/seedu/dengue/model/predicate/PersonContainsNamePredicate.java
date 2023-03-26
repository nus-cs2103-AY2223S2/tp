package seedu.dengue.model.predicate;

import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;

public class PersonContainsNamePredicate implements Predicate<Person> {

    private final Name name;

    public PersonContainsNamePredicate(Name name) {
        this.name = name;
    }

    @Override
    public boolean test(Person person) {
        Predicate<String> containsName =
                keyword -> StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword);

        return containsName.test(name.fullName);
    }
}
