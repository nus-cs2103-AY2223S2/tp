package arb.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import arb.model.client.Client;
import arb.model.project.Deadline;
import arb.model.project.Price;
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

    public static final String DEFAULT_PRICE = "0";

    private Title title;
    private Optional<Deadline> deadline;
    private Optional<Price> price;
    private Set<Tag> tags;
    private boolean isDone;

    private Optional<Client> linkedClient;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadline = Optional.of(new Deadline(DEFAULT_DEADLINE));
        price = Optional.of(new Price(DEFAULT_PRICE));
        tags = new HashSet<>();
        isDone = false;
        linkedClient = Optional.ofNullable(null);
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        title = projectToCopy.getTitle();
        deadline = Optional.ofNullable(projectToCopy.getDeadline());
        price = Optional.ofNullable(projectToCopy.getPrice());
        tags = new HashSet<>(projectToCopy.getTags());
        isDone = projectToCopy.getStatus().getStatus();
        linkedClient = projectToCopy.getLinkedClient();
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
     * Sets whether the project to be built is meant to be done.
     */
    public ProjectBuilder withStatus(boolean isDone) {
        this.isDone = isDone;
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
     * Links {@code client} to the {@code Project} being built.
     */
    public ProjectBuilder withLinkedClient(Client client) {
        this.linkedClient = Optional.ofNullable(client);
        return this;
    }

    /**
     * Builds the Project.
     * @return The new Project.
     */
    public Project build() {
        Project project = new Project(title, this.deadline.orElse(null), this.price.orElse(null), tags);
        if (isDone) {
            project.markAsDone();
        }
        linkedClient.ifPresent(c -> project.linkToClient(c));
        return project;
    }

}
