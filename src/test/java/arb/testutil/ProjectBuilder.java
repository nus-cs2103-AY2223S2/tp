package arb.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.model.tag.Tag;
import arb.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_TITLE = "Large Tree";
    public static final String DEFAULT_DEADLINE = "3pm 2000-01-01";

    private Title title;
    private Optional<Deadline> deadline;
    private Set<Tag> tags;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadline = Optional.of(new Deadline(DEFAULT_DEADLINE));
        tags = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        title = projectToCopy.getTitle();
        deadline = Optional.ofNullable(projectToCopy.getDeadline());
        tags = new HashSet<>(projectToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Project} being built.
     * @param title Title to set.
     * @return The ProjectBuilder object.
     */
    public ProjectBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} being built.
     * @param deadline Deadline to set.
     * @return The ProjectBuilder object.
     */
    public ProjectBuilder withDeadline(String deadline) {
        this.deadline = Optional.ofNullable(deadline).map(d -> new Deadline(d));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Builds the Project.
     * @return The new Project.
     */
    public Project build() {
        return new Project(title, this.deadline.orElse(null), tags);
    }

}
