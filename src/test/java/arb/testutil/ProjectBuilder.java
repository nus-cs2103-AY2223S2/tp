package arb.testutil;

import java.util.Optional;

import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_TITLE = "Large Tree";
    public static final String DEFAULT_DEADLINE = "3pm 2000-01-01";

    private Title title;
    private Optional<Deadline> deadline;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadline = Optional.of(new Deadline(DEFAULT_DEADLINE));
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        title = projectToCopy.getTitle();
        deadline = Optional.ofNullable(projectToCopy.getDeadline());
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
     * Builds the Project.
     * @return The new Project.
     */
    public Project build() {
        return new Project(title, this.deadline.orElse(null));
    }

}
