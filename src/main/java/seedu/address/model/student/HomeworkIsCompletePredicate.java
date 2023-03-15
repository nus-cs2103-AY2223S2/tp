package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Homework}'s {@code isCompleted} matches the given boolean.
 */
public class HomeworkIsCompletePredicate implements Predicate<Homework> {
    private final boolean isComplete;

    /**
     * Creates a predicate to test if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param isComplete The boolean to test against.
     */
    public HomeworkIsCompletePredicate(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param homework The homework to test.
     * @return True if the homework's isCompleted matches the given boolean.
     */
    @Override
    public boolean test(Homework homework) {
        return homework.isCompleted() == isComplete;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HomeworkIsCompletePredicate // instanceof handles nulls
                && isComplete == ((HomeworkIsCompletePredicate) other).isComplete); // state check
    }
}
