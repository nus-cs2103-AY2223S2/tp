package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.variant.Variant;
import seedu.dengue.testutil.PersonBuilder;

public class PersonContainsVariantsPredicateTest {

    private static final List<Variant> validVariantList = Arrays.asList(new Variant("DENV1"));
    private static final List<Variant> validVariantsList =
            Arrays.asList(new Variant("DENV1"), new Variant("DENV2"));
    private final Set<Variant> emptyVariants = new HashSet<>();
    private final Set<Variant> validVariant = new HashSet<>(validVariantList);
    private final Set<Variant> validVariants = new HashSet<>(validVariantsList);

    //Test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new PersonContainsVariantsPredicate(null));
    }

    //Test valid age
    @Test
    public void test_personContainsVariant_returnsTrue() {
        PersonContainsVariantsPredicate predicate = new PersonContainsVariantsPredicate(validVariant);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withVariants("DENV1")
                        .build()));
    }

    @Test
    public void test_personContainsVariant_returnsFalse() {
        PersonContainsVariantsPredicate predicate = new PersonContainsVariantsPredicate(validVariants);
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withVariants("DENV2")
                        .build()));
    }

    @Test
    public void test_personContainsVariants_returnsTrue() {
        PersonContainsVariantsPredicate predicate = new PersonContainsVariantsPredicate(validVariants);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withVariants("DENV1", "DENV2")
                        .build()));
    }

    @Test
    public void test_personContainsVariants_returnsFalse() {
        PersonContainsVariantsPredicate predicate = new PersonContainsVariantsPredicate(validVariants);
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withVariants("DENV1", "DENV3")
                        .build()));
    }

    @Test
    public void test_personContainsEmptyVariant_returnsTrue() {
        PersonContainsVariantsPredicate predicate = new PersonContainsVariantsPredicate(emptyVariants);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withVariants("DENV1")
                        .build()));
    }
}
