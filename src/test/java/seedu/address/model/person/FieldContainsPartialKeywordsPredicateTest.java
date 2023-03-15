package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATOON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATOON_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RANK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RANK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FieldContainsPartialKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstTagKeywordList = Collections.singletonList(VALID_TAG_FRIEND);
        FieldContainsPartialKeywordsPredicate firstPredicateAmy = new FieldContainsPartialKeywordsPredicate(
                VALID_NAME_AMY, VALID_PHONE_AMY, VALID_EMAIL_AMY, VALID_ADDRESS_AMY, VALID_RANK_AMY, VALID_UNIT_AMY,
                VALID_COMPANY_AMY, VALID_PLATOON_AMY, firstTagKeywordList
        );

        List<String> secondTagKeywordList = Arrays.asList(VALID_TAG_FRIEND, VALID_TAG_HUSBAND);
        FieldContainsPartialKeywordsPredicate secondPredicateBob = new FieldContainsPartialKeywordsPredicate(
                VALID_NAME_BOB, VALID_PHONE_BOB, VALID_EMAIL_BOB, VALID_ADDRESS_BOB, VALID_RANK_BOB, VALID_UNIT_BOB,
                VALID_COMPANY_BOB, VALID_PLATOON_BOB, secondTagKeywordList
        );

        // same object -> returns true
        assertTrue(firstPredicateAmy.equals(firstPredicateAmy));

        // same values -> returns true
        FieldContainsPartialKeywordsPredicate firstPredicateAmyCopy = new FieldContainsPartialKeywordsPredicate(
                VALID_NAME_AMY, VALID_PHONE_AMY, VALID_EMAIL_AMY, VALID_ADDRESS_AMY, VALID_RANK_AMY, VALID_UNIT_AMY,
                VALID_COMPANY_AMY, VALID_PLATOON_AMY, firstTagKeywordList
        );
        assertTrue(firstPredicateAmy.equals(firstPredicateAmyCopy));

        // different types -> returns false
        assertFalse(firstPredicateAmy.equals(1));

        // null -> returns false
        assertFalse(firstPredicateAmy.equals(null));

        // different person -> returns false
        assertFalse(firstPredicateAmy.equals(secondPredicateBob));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}

