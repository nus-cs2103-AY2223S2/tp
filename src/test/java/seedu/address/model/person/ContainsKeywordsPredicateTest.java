package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.PersonBuilder;

public class ContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        create_equals(
                CliSyntax.PREFIX_NAME,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
        create_equals(
                CliSyntax.PREFIX_ADDRESS,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
        create_equals(
                CliSyntax.PREFIX_EMAIL,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
        create_equals(
                CliSyntax.PREFIX_TELEGRAM_HANDLE,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
        create_equals(
                CliSyntax.PREFIX_PHONE,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
        create_equals(
                CliSyntax.PREFIX_MODULE_TAG,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
        create_equals(
                CliSyntax.PREFIX_GROUP_TAG,
                firstPredicateKeywordList,
                secondPredicateKeywordList
        );
    }

    public void create_equals(
            Prefix prefix,
            List<String> firstPredicateKeywordList,
            List<String> secondPredicateKeywordList) {
        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(
                firstPredicateKeywordList, prefix);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(
                secondPredicateKeywordList, prefix);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy = new ContainsKeywordsPredicate(
                firstPredicateKeywordList, prefix);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_containsKeywords_returnsTrue() {
        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_NAME,
                new PersonBuilder().withName("Albert Bart").build(),
                Collections.singletonList("Albert"),
                Arrays.asList("Albert", "Bart"),
                Arrays.asList("Bart", "Carol"),
                Arrays.asList("alBert", "bArT")
        );

        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_PHONE,
                new PersonBuilder().withPhone("89760441").build(),
                Collections.singletonList("89760441"), // Full number
                Arrays.asList("8976", "0441"), // Number split into 2 parts by user
                Arrays.asList("8976", "2028"), // Number split into 2 parts by user, only first part matching
                Arrays.asList("8976", "0441") // Mixed case keywords does not apply for numbers
        );

        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_EMAIL,
                new PersonBuilder().withEmail("albertpark@gmail.com").build(),
                Collections.singletonList("albertpark@gmail.com"),
                Arrays.asList("albertpark", "@gmail.com"),
                Arrays.asList("albertpark", "@yahoo.com"), // Email split into 2 parts by user, only first part matching
                Arrays.asList("aLberTPark@GmaIL.com")
        );

        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_ADDRESS,
                new PersonBuilder().withAddress("2 Blackmore Drive").build(),
                Collections.singletonList("Blackmore"),
                Arrays.asList("2", "Blackmore", "Drive"),
                Arrays.asList("2", "Blackmore", "Street"),
                Arrays.asList("2", "bLackMore", "drIve")
        );

        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_TELEGRAM_HANDLE,
                new PersonBuilder().withTelegramHandle("@albertpark").build(),
                Collections.singletonList("@albertpark"),
                Arrays.asList("@albert", "park"),
                Arrays.asList("@albert", "bart"),
                Arrays.asList("@albErtParK")
        );

        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_GROUP_TAG,
                new PersonBuilder().withGroupTags("TA", "CCA").build(),
                Collections.singletonList("TA"),
                Arrays.asList("TA", "CCA"),
                Arrays.asList("TA", "Study"),
                Arrays.asList("tA", "cCa")
        );

        create_containsKeywords_returnsTrue(
                CliSyntax.PREFIX_MODULE_TAG,
                new PersonBuilder().withModuleTags("CE2183", "CS1010R").build(),
                Collections.singletonList("CE2183"),
                Arrays.asList("CE2183", "CS1010R"),
                Arrays.asList("CE2183", "CS1231S"),
                Arrays.asList("cE2183", "cS1010r")
        );
    }

    public void create_containsKeywords_returnsTrue(
            Prefix prefix, Person person, List<String> oneKeyword, List<String> multiKeyword,
            List<String> oneMatchKeyword, List<String> mixedCaseKeyword) {
        // One keyword
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(
                oneKeyword, prefix);
        assertTrue(predicate.test(person));

        // Multiple keywords
        predicate = new ContainsKeywordsPredicate(multiKeyword, prefix);
        assertTrue(predicate.test(person));

        // Only one matching keyword
        predicate = new ContainsKeywordsPredicate(oneMatchKeyword, prefix);
        assertTrue(predicate.test(person));

        // Mixed-case keywords
        predicate = new ContainsKeywordsPredicate(mixedCaseKeyword, prefix);
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        List<String> emptyList = Collections.emptyList();
        Person person = new PersonBuilder().withName("Albert").withPhone("12345").withEmail("alice@email.com")
                .withAddress("Main Street").withTelegramHandle("@albertpark")
                .withModuleTags("CS2030S", "CS2103T", "CS2101").withGroupTags("TA", "CCA").build();

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_NAME,
                person,
                emptyList,
                List.of("Carol"),
                Arrays.asList("12345", "alice@email.com", "Main", "Street")
        );

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_TELEGRAM_HANDLE,
                person,
                emptyList,
                List.of("@alice"),
                Arrays.asList("12345", "alice@email.com", "Main", "Street")
        );

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_EMAIL,
                person,
                emptyList,
                List.of("albertpark@gmail.com"),
                Arrays.asList("Albert", "12345", "Main", "Street")
        );

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_ADDRESS,
                person,
                emptyList,
                List.of("Side", "Drive"),
                Arrays.asList("Albert", "12345", "@albertpark", "TA")
        );

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_PHONE,
                person,
                emptyList,
                List.of("67890"),
                Arrays.asList("Albert", "alice@email.com", "@albertpark", "CS2030S")
        );

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_MODULE_TAG,
                person,
                emptyList,
                List.of("CS3230"),
                Arrays.asList("Albert", "alice@email.com", "@albertpark", "TA")
        );

        create_doesNotContainKeywords_returnsFalse(
                CliSyntax.PREFIX_GROUP_TAG,
                person,
                emptyList,
                List.of("Study"),
                Arrays.asList("Albert", "alice@email.com", "@albertpark", "CS2030S")
        );
    }

    public void create_doesNotContainKeywords_returnsFalse(
            Prefix prefix, Person person, List<String> emptyList, List<String> wrongKeyword, List<String> wrongFields) {
        // Zero keywords
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(emptyList, prefix);
        assertFalse(predicate.test(person));

        // Non-matching keyword
        predicate = new ContainsKeywordsPredicate(wrongKeyword, prefix);
        assertFalse(predicate.test(person));

        // Keywords match other fields, but does not match prefix
        predicate = new ContainsKeywordsPredicate(wrongFields, prefix);
        assertFalse(predicate.test(person));
    }
}
