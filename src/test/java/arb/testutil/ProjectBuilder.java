package arb.testutil;

import java.util.Optional;

import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Title;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_TITLE = "Large Tree";
    public static final String DEFAULT_DEADLINE = "3pm 2000-01-01";

    public static final String DEFAULT_PRICE = "0";

    private Title title;
    private Optional<Deadline> deadline;
    private Optional<Price> price;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadline = Optional.of(new Deadline(DEFAULT_DEADLINE));
        price = Optional.of(new Price(DEFAULT_PRICE));
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        title = projectToCopy.getTitle();
        deadline = Optional.ofNullable(projectToCopy.getDeadline());
        price = Optional.ofNullable(projectToCopy.getPrice());
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
     * Sets the {@code Price} of the {@code Project} being built.
     * @param price Price to set.
     * @return The ProjectBuilder object.
     */
    public ProjectBuilder withPrice(String price) {
        this.price = Optional.ofNullable(price).map(pr -> new Price(pr));
        return this;
    }

    /**
     * Builds the Project.
     * @return The new Project.
     */
    public Project build() {
        return new Project(title, this.deadline.orElse(null), this.price.orElse(null));
    }

}
