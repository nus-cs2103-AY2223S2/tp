package arb.testutil;

import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_TITLE = "Large Tree";
    public static final String DEFAULT_DEADLINE = "2000-01-01";

    private Title title;
    private Deadline deadline;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code personToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        title = projectToCopy.getTitle();
        deadline = projectToCopy.getDeadline();
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
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Builds the Project.
     * @return The new Project.
     */
    public Project build() {
        return new Project(title, deadline);
    }

}
