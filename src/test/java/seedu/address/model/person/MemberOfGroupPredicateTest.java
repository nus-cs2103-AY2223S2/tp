package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.Group;
import seedu.address.testutil.PersonBuilder;

class MemberOfGroupPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MemberOfGroupPredicate firstPredicate = new MemberOfGroupPredicate(firstPredicateKeywordList);
        MemberOfGroupPredicate secondPredicate = new MemberOfGroupPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MemberOfGroupPredicate firstPredicateCopy = new MemberOfGroupPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different group -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        Group firstGroup = new Group("CS2103");
        Group secondGroup = new Group("Friends");
        Person personWithOneGroup = new PersonBuilder().withName("First").build();
        personWithOneGroup.addGroup(firstGroup);
        Person personWithTwoGroups = new PersonBuilder().withName("Second").build();
        personWithTwoGroups.addGroup(firstGroup);
        personWithTwoGroups.addGroup(secondGroup);

        // One keyword
        MemberOfGroupPredicate predicate = new MemberOfGroupPredicate(Collections.singletonList("CS2103"));
        assertTrue(predicate.test(personWithOneGroup));

        // Multiple keywords
        predicate = new MemberOfGroupPredicate(Arrays.asList("Friends", "CS2103"));
        assertTrue(predicate.test(personWithTwoGroups));

        // Only one matching keyword
        predicate = new MemberOfGroupPredicate(Arrays.asList("Friends", "CS2103"));
        assertTrue(predicate.test(personWithOneGroup));

        // Mixed-case keywords
        predicate = new MemberOfGroupPredicate(Arrays.asList("friends", "cs2103"));
        assertTrue(predicate.test(personWithTwoGroups));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        Group firstGroup = new Group("CS2103");
        Person personWithOneGroup = new PersonBuilder().withName("First").build();
        personWithOneGroup.addGroup(firstGroup);

        // Zero keywords
        MemberOfGroupPredicate predicate = new MemberOfGroupPredicate(Collections.emptyList());
        assertFalse(predicate.test(personWithOneGroup));

        // Non-matching keyword
        predicate = new MemberOfGroupPredicate(Arrays.asList("NonMatching"));
        assertFalse(predicate.test(personWithOneGroup));
    }
}
