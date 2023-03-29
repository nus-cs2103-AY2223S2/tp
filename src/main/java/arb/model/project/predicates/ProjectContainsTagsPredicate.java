package arb.model.project.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import arb.model.project.Project;
import arb.model.tag.Tag;

/**
 * Tests that a {@code Project} contains any of the tags given.
 */
public class ProjectContainsTagsPredicate implements Predicate<Project> {
    private final Set<Tag> tags;

    public ProjectContainsTagsPredicate(List<String> keywords) {
        tags = keywords.stream().map(s -> new Tag(s.toLowerCase())).collect(Collectors.toSet());
    }

    @Override
    public boolean test(Project project) {
        return tags.stream()
                .anyMatch(t -> project.getTags().contains(t));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectContainsTagsPredicate // instanceof handles nulls
                && tags.equals(((ProjectContainsTagsPredicate) other).tags)); // state check
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }
}
