package seedu.sprint.model.application;

import java.util.function.Predicate;

/**
 * Tests that a {@code Application} has a {@code Task}.
 */
public class ApplicationHasTaskPredicate implements Predicate<Application> {

    @Override
    public boolean test(Application application) {
        return application.hasTask();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ApplicationHasTaskPredicate; // instanceof handles nulls
    }
}
