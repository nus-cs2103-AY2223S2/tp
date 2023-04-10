package arb.model.project;

import static arb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import arb.commons.core.LogsCenter;
import arb.model.client.Client;
import arb.model.tag.Tag;

/**
 * Represents a Project in the address book.
 * Guarantees: title and status is present and not null; field values are validated & immutable.
 */
public class Project {

    private static final Logger logger = LogsCenter.getLogger(Project.class);

    // Details fields. Deadline is optional.
    private final Title title;
    private final Optional<Deadline> deadline;
    private final Optional<Price> price;
    private final Status status;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    // Linked client
    private Optional<Client> linkedClient;

    /**
     * Constructs a {@code Project}.
     * Title and tags must be present and not null.
     */
    public Project(Title title, Deadline deadline, Price price, Set<Tag> tags) {
        requireAllNonNull(title, tags);
        this.title = title;
        this.deadline = Optional.ofNullable(deadline);
        this.price = Optional.ofNullable(price);
        this.tags.addAll(tags);

        this.status = new Status();
        this.linkedClient = Optional.empty();
    }

    /**
     * Contructs a {@code Project}.
     * Only to be used when created a new edited {@code Project}.
     */
    public Project(Title title, Status status, Deadline deadline, Price price, Set<Tag> tags) {
        requireAllNonNull(title, tags);
        this.title = title;
        this.status = status;
        this.deadline = Optional.ofNullable(deadline);
        this.price = Optional.ofNullable(price);
        this.tags.addAll(tags);

        this.linkedClient = Optional.empty();
    }

    public Title getTitle() {
        return title;
    }

    /**
     * Returns true if this project has a deadline.
     */
    public boolean isDeadlinePresent() {
        return deadline.isPresent();
    }

    /**
     * Returns true if this project has a price.
     */
    public boolean isPricePresent() {
        return price.isPresent();
    }

    /**
     * Returns true if this project has a linked client.
     */
    public boolean isClientPresent() {
        return linkedClient.isPresent();
    }

    public Deadline getDeadline() {
        return deadline.orElse(null);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if this project has is overdue
     */
    public boolean isOverdue() {
        if (!isDeadlinePresent()) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        logger.info(currentDate.toString());
        Deadline currentDateAsDeadline = new Deadline(currentDate.toString());
        logger.info(currentDateAsDeadline.toString());
        logger.info(Integer.toString(this.getDeadline().compareTo(currentDateAsDeadline)));
        return this.getDeadline().compareTo(currentDateAsDeadline) < 0 && !this.status.getStatus();
    }

    public Status getStatus() {
        return status;
    }

    public Price getPrice() {
        return price.orElse(null);
    }

    public String getClientName() {
        return linkedClient.map(c -> c.getName().fullName).orElse(null);
    }

    public void markAsDone() {
        this.status.setTrue();
    }

    public void markAsUndone() {
        this.status.setFalse();
    }


    /**
     * Links {@code client} to this project.
     */
    public void linkToClient(Client client) {
        requireNonNull(client);
        linkedClient = Optional.of(client);
    }

    /**
     * Unlinks any linked client from this project.
     */
    public void unlinkFromClient() {
        linkedClient = Optional.empty();
    }

    public Optional<Client> getLinkedClient() {
        return linkedClient;
    }

    /**
     * Returns true if both projects have the same title.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getTitle().equals(getTitle());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle());

        if (isDeadlinePresent()) {
            builder.append(", due by: ").append(getDeadline());
        }

        if (isPricePresent()) {
            builder.append(", ").append(getPrice());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;

        return otherProject.getTitle().equals(getTitle())
                && otherProject.deadline.equals(deadline)
                && otherProject.price.equals(price)
                && otherProject.getStatus().equals(getStatus())
                && otherProject.getTags().equals(getTags())
                && otherProject.linkedClient.equals(linkedClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, deadline, status, price, tags, linkedClient);
    }
}
