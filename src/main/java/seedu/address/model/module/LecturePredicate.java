package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.model.lecture.ReadOnlyLecture;

/**
 * Tests that a {@code ReadOnlyLecture} is in user's input {@code ReadOnlyModule}.
 */
public class LecturePredicate implements Predicate<ReadOnlyLecture> {
    private final ReadOnlyModule module;

    public LecturePredicate(ReadOnlyModule module) {
        this.module = module;
    }

    @Override
    public boolean test(ReadOnlyLecture lecture) {
        boolean res = module.hasLecture(lecture);
        return  res;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LecturePredicate // instanceof handles nulls
                && module.equals(((LecturePredicate) other).module)); // state check
    }
}
