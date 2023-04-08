package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
public class PhoneContainsKeywordsPredicateTest {
    @Test
    public void test_personPhoneInList_shouldReturnTrue() {
        PhoneContainsKeywordsPredicate pred =
                new PhoneContainsKeywordsPredicate(List.of("99991234", "12349999"));
        Person person = new PersonBuilder().withPhone("99991234").build();

        assertTrue(pred.test(person));
    }

    @Test
    public void test_personPhoneNotInList_shouldReturnFalse() {
        PhoneContainsKeywordsPredicate pred =
                new PhoneContainsKeywordsPredicate(List.of("99991234", "12349999"));
        Person person = new PersonBuilder().withPhone("11111111").build();

        assertFalse(pred.test(person));
    }

    @Test
    public void test_partialPhoneNumber_shouldReturnFalse() {
        PhoneContainsKeywordsPredicate pred =
                new PhoneContainsKeywordsPredicate(List.of("99991234"));
        Person person = new PersonBuilder().withPhone("9999123").build();

        assertFalse(pred.test(person));
    }

    @Test
    public void equals() {
        PhoneContainsKeywordsPredicate pred1 =
                new PhoneContainsKeywordsPredicate(List.of("1234", "9999"));
        PhoneContainsKeywordsPredicate pred2 =
                new PhoneContainsKeywordsPredicate(List.of("1234", "9999"));
        PhoneContainsKeywordsPredicate pred3 =
                new PhoneContainsKeywordsPredicate(List.of("1234"));
        PhoneContainsKeywordsPredicate pred4 =
                new PhoneContainsKeywordsPredicate(List.of("5678"));

        assertTrue(pred1.equals(pred1));
        assertTrue(pred1.equals(pred2));
        assertFalse(pred1.equals(pred3));
        assertFalse(pred1.equals(pred4));
        assertFalse(pred1.equals(null));
    }
}
