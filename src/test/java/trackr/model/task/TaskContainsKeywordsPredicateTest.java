package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.testutil.TaskBuilder;
import trackr.testutil.TaskPredicateBuilder;

public class TaskContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskContainsKeywordsPredicate firstPredicate =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(firstPredicateKeywordList)
                        .withTaskDeadline("01/01/2024")
                        .withTaskStatus("N")
                        .build();
        TaskContainsKeywordsPredicate secondPredicate;

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsKeywordsPredicate firstPredicateCopy =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(firstPredicateKeywordList)
                        .withTaskDeadline("01/01/2024")
                        .withTaskStatus("N")
                        .build();
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different taskNameKeywords -> returns false
        secondPredicate =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(secondPredicateKeywordList)
                        .withTaskDeadline("01/01/2024")
                        .withTaskStatus("N")
                        .build();
        assertFalse(firstPredicate.equals(secondPredicate));

        // different taskDeadline -> returns false
        secondPredicate =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(firstPredicateKeywordList)
                        .withTaskDeadline("11/11/2024")
                        .withTaskStatus("N")
                        .build();
        assertFalse(firstPredicate.equals(secondPredicate));

        // different taskStatus -> returns false
        secondPredicate =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(firstPredicateKeywordList)
                        .withTaskDeadline("01/01/2024")
                        .withTaskStatus("D")
                        .build();
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_oneVariableMatch_returnsTrue() {
        TaskContainsKeywordsPredicate predicate;

        // One keyword
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Collections.singletonList("Buy")).build();
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Buy Food").build()));

        // Multiple keywords
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Arrays.asList("Buy", "Food")).build();
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Buy Food").build()));

        // Only one matching keyword
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Arrays.asList("Buy", "flour")).build();
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Buy Food").build()));

        // Mixed-case keywords
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Arrays.asList("BuY", "FloUr")).build();
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Buy Flour").build()));

        // Deadline only
        predicate =
                new TaskPredicateBuilder().withTaskDeadline("01/01/2023").build();
        assertTrue(predicate.test(new TaskBuilder().withTaskDeadline("01/01/2023").build()));

        // Status only
        predicate =
                new TaskPredicateBuilder().withTaskStatus("N").build();
        assertTrue(predicate.test(new TaskBuilder().withTaskStatus("N").build()));
    }

    @Test
    public void test_oneVariableDoesNotMatch_returnsFalse() {
        TaskContainsKeywordsPredicate predicate;
        Task testTask;

        // Zero keywords
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Collections.emptyList()).build();
        assertFalse(predicate.test(new TaskBuilder().withTaskName("Buy").build()));

        // Non-matching keyword
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Arrays.asList("Buy")).build();
        assertFalse(predicate.test(new TaskBuilder().withTaskName("Sort Inventory").build()));

        // Keywords match deadline and status, but does not match name
        predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(Arrays.asList("11/11/2024", "N")).build();
        assertFalse(predicate.test(new TaskBuilder().withTaskName("Buy").withTaskDeadline("11/11/2024")
                .withTaskStatus("N").build()));

        // Non-matching deadline
        predicate =
                new TaskPredicateBuilder().withTaskDeadline("01/01/2023").build();
        assertFalse(predicate.test(new TaskBuilder().withTaskDeadline("11/11/2023").build()));

        // Match name and status, but not deadline
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Buy", "Flour"))
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        testTask = new TaskBuilder()
                .withTaskName("Buy Flour")
                .withTaskStatus("N")
                .withTaskDeadline("11/11/2023")
                .build();
        assertFalse(predicate.test(testTask));

        // Non-matching status
        predicate =
                new TaskPredicateBuilder().withTaskStatus("N").build();
        assertFalse(predicate.test(new TaskBuilder().withTaskStatus("D").build()));

        // Match name and deadline, but not status
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Buy", "Flour"))
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        testTask = new TaskBuilder()
                .withTaskName("Buy Flour")
                .withTaskStatus("D")
                .withTaskDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));
    }

    @Test
    public void test_twoVariableMatch_returnsTrue() {
        TaskContainsKeywordsPredicate predicate;

        // Same name and deadline
        predicate = new TaskPredicateBuilder()
                .withTaskDeadline("01/01/2023")
                .withTaskNameKeywords(Arrays.asList("Buy", "Food"))
                .build();
        assertTrue(predicate.test(new TaskBuilder().withTaskDeadline("01/01/2023").withTaskName("Buy Food").build()));

        // Same deadline and status
        predicate = new TaskPredicateBuilder()
                .withTaskDeadline("01/01/2023")
                .withTaskStatus("N")
                .build();
        assertTrue(predicate.test(new TaskBuilder().withTaskDeadline("01/01/2023").withTaskStatus("N").build()));

        // Same name and status
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Buy", "Food"))
                .withTaskStatus("N")
                .build();
        Task testTask = new TaskBuilder()
                .withTaskName("Buy Food")
                .withTaskStatus("N")
                .build();
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_twoVariableDoesNotMatch_returnsFalse() {
        TaskContainsKeywordsPredicate predicate;
        Task testTask;

        // Same name, different deadline and status
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Buy", "Food"))
                .withTaskStatus("D")
                .withTaskDeadline("11/11/2023")
                .build();
        testTask = new TaskBuilder()
                .withTaskName("Buy Food")
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));

        // Same deadline, different name and status
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Sort", "Inventory"))
                .withTaskStatus("D")
                .withTaskDeadline("01/01/2023")
                .build();
        testTask = new TaskBuilder()
                .withTaskName("Buy Food")
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));

        // Same status, different name and deadline
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Sort", "Inventory"))
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        testTask = new TaskBuilder()
                .withTaskName("Buy Food")
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));
    }

    @Test
    public void test_threeVariableMatch_returnsTrue() {
        TaskContainsKeywordsPredicate predicate;

        // Same name, deadline and status
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Buy", "Food"))
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        Task testTask = new TaskBuilder()
                .withTaskName("Buy Food")
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_threeVariableDoesNotMatch_returnsFalse() {
        TaskContainsKeywordsPredicate predicate;

        // Different name, deadline and status
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Sort", "Inventory"))
                .withTaskStatus("D")
                .withTaskDeadline("11/11/2023")
                .build();
        Task testTask = new TaskBuilder()
                .withTaskName("Buy Food")
                .withTaskStatus("N")
                .withTaskDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));
    }

    @Test
    public void test_atLeastOneFieldPresent_returnsTrue() {
        TaskContainsKeywordsPredicate predicate;

        // taskNameKeywords present
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Arrays.asList("Sort", "Inventory"))
                .build();
        assertTrue(predicate.isAnyFieldPresent());

        // taskDeadline present
        predicate = new TaskPredicateBuilder()
                .withTaskDeadline("01/01/2022")
                .build();
        assertTrue(predicate.isAnyFieldPresent());

        // taskStatus present
        predicate = new TaskPredicateBuilder()
                .withTaskStatus("N")
                .build();
        assertTrue(predicate.isAnyFieldPresent());
    }

    @Test
    public void test_noFieldPresent_returnsFalse() {
        TaskContainsKeywordsPredicate predicate;

        // taskNameKeywords present
        predicate = new TaskPredicateBuilder().build();
        assertFalse(predicate.isAnyFieldPresent());
    }
}
