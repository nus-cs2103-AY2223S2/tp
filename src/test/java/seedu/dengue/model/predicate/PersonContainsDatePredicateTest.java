package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.person.Date;
import seedu.dengue.testutil.PersonBuilder;

public class PersonContainsDatePredicateTest {

    private final Optional<Date> emptyDate = Optional.empty();
    private final Optional<Date> validDate = Optional.of(new Date("2011-11-11"));

    //Test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new PersonContainsDatePredicate(null));
    }

    //Test valid date
    @Test
    public void test_personContainsDate_returnsTrue() {
        PersonContainsDatePredicate predicate = new PersonContainsDatePredicate(validDate);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withDate("2011-11-11")
                        .build()));
    }

    @Test
    public void test_personContainsDate_returnsFalse() {
        PersonContainsDatePredicate predicate = new PersonContainsDatePredicate(validDate);
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withDate("2011-10-10")
                        .build()));
    }

    @Test
    public void test_personContainsEmptyDate_returnsTrue() {
        PersonContainsDatePredicate predicate = new PersonContainsDatePredicate(emptyDate);
        assertTrue(predicate.test(
                new PersonBuilder()
                        .withDate("2011-10-10")
                        .build()));
    }
}
