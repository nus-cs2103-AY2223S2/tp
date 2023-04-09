package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.person.Age;
import seedu.dengue.testutil.PersonBuilder;

public class PersonContainsAgePredicateTest {

    private final Optional<Age> emptyAge = Optional.empty();
    private final Optional<Age> validAge = Optional.of(new Age("15"));

    //Test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new PersonContainsAgePredicate(null));
    }

    //Test valid age
    @Test
    public void test_personContainsAge_returnsTrue() {
        PersonContainsAgePredicate predicate = new PersonContainsAgePredicate(validAge);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withAge("15")
                        .build()));
    }

    @Test
    public void test_personContainsAge_returnsFalse() {
        PersonContainsAgePredicate predicate = new PersonContainsAgePredicate(validAge);
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withAge("20")
                        .build()));
    }

    @Test
    public void test_personContainsEmptyAge_returnsTrue() {
        PersonContainsAgePredicate predicate = new PersonContainsAgePredicate(emptyAge);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withAge("20")
                        .build()));
    }
}
