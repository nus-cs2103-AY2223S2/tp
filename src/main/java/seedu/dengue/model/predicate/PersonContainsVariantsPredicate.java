package seedu.dengue.model.predicate;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.dengue.model.person.Person;
import seedu.dengue.model.variant.Variant;

/**
 * Represents the predicate which tests for whether the person in the persons list has a valid variant based
 * on the variant made using the user input.
 */
public class PersonContainsVariantsPredicate extends PredicateUtil<Person> {

    private final Set<Variant> variants;

    /**
     * Constructs a predicate used to test whether the variants are a part of a person in the case list.
     * @param variants a set of variants which is used to test with a person.
     */
    public PersonContainsVariantsPredicate(Set<Variant> variants) {
        requireNonNull(variants);
        this.variants = variants;
    }
    @Override
    public boolean test(Person person) {
        if (person.getVariants().containsAll(variants)) {
            return true;
        }
        return false;
    }
}
