package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.Set;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.variant.Variant;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindPredicate extends PredicateUtil<Person> {
    private final Optional<Name> name;
    private final Optional<SubPostal> subPostal;
    private final Optional<Age> age;
    private final Optional<Date> date;
    private final Set<Variant> variants;
    private final Range<Date> dateRange;
    private final Range<Age> ageRange;

    /**
     * A class that represents the predicate which is used to filter through the persons list to test for whether
     * the person belongs to the filtered list.
     * @param name (Optional) Name of the input passed in by user.
     * @param subPostal (Optional) Substring of a {@code Postal} of the input passed in by user.
     * @param age (Optional) Age of the input passed in by user.
     * @param date (Optional) Date of the input passed in by user.
     * @param variants Set of Variant of the input passed in by user.
     * @param dateRange Range containing (Optional) Date of the input passed in by user.
     * @param ageRange Range containing (Optional) Age of the input passed in by user.
     */

    public FindPredicate(Optional<Name> name, Optional<SubPostal> subPostal, Optional<Age> age, Optional<Date> date,
                         Set<Variant> variants, Range<Date> dateRange, Range<Age> ageRange) {
        this.name = name;
        this.subPostal = subPostal;
        this.age = age;
        this.date = date;
        this.variants = variants;
        this.dateRange = dateRange;
        this.ageRange = ageRange;
    }

    private boolean isEveryPrefixEmpty(Optional<Name> name, Optional<SubPostal> subPostal,
                                       Optional<Age> age, Optional<Date> date, Set<Variant> variants,
                                       Range<Date> dateRange, Range<Age> ageRange) {
        boolean isNameEmpty = name.isEmpty();
        boolean isSubPostalEmpty = subPostal.isEmpty();
        boolean isAgeEmpty = age.isEmpty();
        boolean isDateEmpty = date.isEmpty();
        boolean isVariantsEmpty = variants.isEmpty();
        boolean isDateRangeEmpty = dateRange.isRangeEmpty();
        boolean isAgeRangeEmpty = ageRange.isRangeEmpty();
        return isNameEmpty && isSubPostalEmpty && isAgeEmpty && isDateEmpty && isVariantsEmpty && isDateRangeEmpty
                && isAgeRangeEmpty;
    }

    @Override
    public boolean test(Person person) {
        assert !(isEveryPrefixEmpty(name, subPostal, age, date, variants, dateRange, ageRange));
        PersonContainsNamePredicate hasName = new PersonContainsNamePredicate(name);
        PersonContainsAgePredicate hasAge = new PersonContainsAgePredicate(age);
        PersonContainsPostalPredicate hasPostal = new PersonContainsPostalPredicate(subPostal);
        PersonContainsDatePredicate hasDate = new PersonContainsDatePredicate(date);
        PersonContainsVariantsPredicate hasVariants = new PersonContainsVariantsPredicate(variants);
        RangeContainsPersonPredicate hasDateRange = new RangeContainsPersonPredicate(dateRange);
        RangeContainsPersonPredicate hasAgeRange = new RangeContainsPersonPredicate(ageRange);
        return andAll(
                hasName, hasAge, hasDate, hasVariants, hasPostal, hasDateRange, hasAgeRange).test(person);
    }
}
