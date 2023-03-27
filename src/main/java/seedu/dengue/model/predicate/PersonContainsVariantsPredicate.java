package seedu.dengue.model.predicate;

import java.util.Set;
import java.util.function.Predicate;

import seedu.dengue.model.person.Person;
import seedu.dengue.model.variant.Variant;

public class PersonContainsVariantsPredicate implements Predicate<Person> {

    private final Set<Variant> variants;

    public PersonContainsVariantsPredicate(Set<Variant> variants) {
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
