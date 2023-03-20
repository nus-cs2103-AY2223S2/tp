package seedu.socket.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.socket.commons.util.StringUtil;
import seedu.socket.model.project.Project;

/**
 * Tests that a {@code Project}'s {@code ProjectName} matches any of the keywords given.
 */
public class FindCommandProjectNamePredicate implements Predicate<Project> {
    private final List<String> keywords;

    public FindCommandProjectNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Project project) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(project.getName().projectName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommandProjectNamePredicate // instanceof handles nulls
                && keywords.equals(((FindCommandProjectNamePredicate) other).keywords)); // state check
    }

}
