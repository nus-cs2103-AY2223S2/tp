package arb.model.project.predicates;

import java.util.function.Predicate;

import arb.model.project.Project;
import arb.model.project.Status;

/**
 * Tests that a {@code Project} is of the given {@code Status}.
 */
public class IsOfStatusPredicate implements Predicate<Project> {

    private final Status statusToCheck;

    /**
     * Constructs a {@code IsOfStatusPredicate} using the given {@code Status}.
     */
    public IsOfStatusPredicate(Status status) {
        statusToCheck = status;
    }

    @Override
    public boolean test(Project project) {
        return project.getStatus().equals(statusToCheck);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsOfStatusPredicate // handles null
                && statusToCheck.equals(((IsOfStatusPredicate) other).statusToCheck));
    }

    @Override
    public int hashCode() {
        return statusToCheck.hashCode();
    }
}
