package arb.model.project.predicates;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import arb.model.project.Deadline;
import arb.model.project.Project;

/**
 * Tests that a {@code Project}'s deadline falls within the given timeframe.
 */
public class ProjectWithinTimeframePredicate implements Predicate<Project> {
    private final Optional<Deadline> start;
    private final Optional<Deadline> end;

    /**
    * Builds a {@code ProjectWithinTimeframePredicate} that tests if a
    * {@code Project}'s deadline falls within the timeframe {@code start} to
    * {@code end}.
    */
    public ProjectWithinTimeframePredicate(Deadline start, Deadline end) {
        this.start = Optional.ofNullable(start);
        this.end = Optional.ofNullable(end);
    }

    @Override
    public boolean test(Project project) {
        return project.isDeadlinePresent()
            && start.map(d -> project.getDeadline().compareTo(d) >= 0).orElse(true)
            && end.map(d -> d.compareTo(project.getDeadline()) >= 0).orElse(true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectWithinTimeframePredicate // instanceof handles nulls
                && start.equals(((ProjectWithinTimeframePredicate) other).start) // state check
                && end.equals(((ProjectWithinTimeframePredicate) other).end));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (start.isPresent()) {
            sb.append("Start of timeframe: " + start.get().toString());
        }
        if (start.isPresent() && end.isPresent()) {
            sb.append(", ");
        }
        if (end.isPresent()) {
            sb.append("End of timeframe: " + end.get().toString());
        }
        return sb.toString();
    }
}
