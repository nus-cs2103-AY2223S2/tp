package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.variant.Variant;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindPredicate implements Predicate<Person> {
    private final Optional<Name> name;
    private final Optional<SubPostal> subPostal;
    private final Optional<Age> age;
    private final Optional<Date> date;
    private final Set<Variant> variants;

    /**
     * A class that represents the predicate which is used to filter through the persons list to test for whether
     * the person belongs to the filtered list.
     *
     * @param name
     * @param subPostal
     * @param age
     * @param date
     * @param variants
     */
    public FindPredicate(Optional<Name> name, Optional<SubPostal> subPostal, Optional<Age> age, Optional<Date> date,
                         Set<Variant> variants) {
        this.name = name;
        this.subPostal = subPostal;
        this.age = age;
        this.date = date;
        this.variants = variants;
    }

    @Override
    public boolean test(Person person) {
        boolean containsName = new PersonContainsNamePredicate(name).test(person);
        boolean containsPostal = new PersonContainsPostalPredicate(subPostal).test(person);
        boolean containsAge = new PersonContainsAgePredicate(age).test(person);
        boolean containsDate = new PersonContainsDatePredicate(date).test(person);
        boolean containsVariants = new PersonContainsVariantsPredicate(variants).test(person);
        return containsName && containsPostal && containsAge && containsDate && containsVariants;
    }
}
