package seedu.dengue.model.predicate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.variant.Variant;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindPredicate implements Predicate<Person> {
    private final Optional<Name> name;
    private final Optional<Postal> postal;
    private final Optional<Age> age;
    private final Optional<Date> date;
    private final Set<Variant> variants;

    public FindPredicate(Optional<Name> name, Optional<Postal> postal, Optional<Age> age, Optional<Date> date
            , Set<Variant> variants) {
        this.name = name;
        this.postal = postal;
        this.age = age;
        this.date = date;
        this.variants = variants;
    }

    @Override
    public boolean test(Person person) {
        boolean containsName = new PersonContainsNamePredicate(name).test(person);
        boolean containsPostal = new PersonContainsPostalPredicate(postal).test(person);
        boolean containsAge = new PersonContainsAgePredicate(age).test(person);
        boolean containsDate = new PersonContainsDatePredicate(date).test(person);
        boolean containsVariants = new PersonContainsVariantsPredicate(variants).test(person);
        return containsName && containsPostal && containsAge && containsDate && containsVariants;
    }
}
