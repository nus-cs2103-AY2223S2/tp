package seedu.address.model.application;

import java.util.function.Predicate;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipStatus}
 * matches the specified status given.
 */
public class StatusPredicate implements Predicate<InternshipApplication> {
    private final InternshipStatus status;

    public StatusPredicate(InternshipStatus status) {
        this.status = status;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        return internshipApplication.getStatus() == status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusPredicate // instanceof handles nulls
                && status.equals(((StatusPredicate) other).status)); // state check
    }
}
