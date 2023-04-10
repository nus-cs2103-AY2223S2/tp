package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.person.SubPostal;
import seedu.dengue.testutil.PersonBuilder;

public class PersonContainsPostalPredicateTest {

    private final Optional<SubPostal> emptySubPostal = Optional.empty();
    private final Optional<SubPostal> validSubPostal = Optional.of(new SubPostal("s123"));
    private final Optional<SubPostal> validPostal = Optional.of(new SubPostal("s123456"));

    //Test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new PersonContainsPostalPredicate(null));
    }

    //Test valid SubPostal
    @Test
    public void test_personContainsSubPostal_returnsTrue() {
        PersonContainsPostalPredicate predicate = new PersonContainsPostalPredicate(validSubPostal);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withPostal("123456")
                        .build()));
    }

    //Test valid Postal
    @Test
    public void test_personContainsFullPostal_returnsTrue() {
        PersonContainsPostalPredicate predicate = new PersonContainsPostalPredicate(validPostal);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withPostal("123456")
                        .build()));
    }

    //Test valid SubPostal
    @Test
    public void test_personContainsFullPostal_returnsFalse() {
        PersonContainsPostalPredicate predicate = new PersonContainsPostalPredicate(validPostal);
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withPostal("223456")
                        .build()));
    }

    @Test
    public void test_personContainsSubPostal_returnsFalse() {
        PersonContainsPostalPredicate predicate = new PersonContainsPostalPredicate(validSubPostal);
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withPostal("231237")
                        .build()));
    }

    @Test
    public void test_personContainsEmptySubPostal_returnsTrue() {
        PersonContainsPostalPredicate predicate = new PersonContainsPostalPredicate(emptySubPostal);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withPostal("123456")
                        .build()));
    }
}
